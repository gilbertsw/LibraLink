package id.co.libralink.borrowing.service.impl;

import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.apigateway.account.service.UserService;
import id.co.libralink.book.model.entity.Book;
import id.co.libralink.book.service.BookService;
import id.co.libralink.borrowing.mapper.BorrowingDetailMapper;
import id.co.libralink.borrowing.mapper.BorrowingListMapper;
import id.co.libralink.borrowing.model.entity.Borrowing;
import id.co.libralink.borrowing.model.enums.BorrowStatus;
import id.co.libralink.borrowing.model.request.BorrowBookRequest;
import id.co.libralink.borrowing.model.request.SearchBorrowingRequest;
import id.co.libralink.borrowing.model.response.BorrowingDetailResponse;
import id.co.libralink.borrowing.model.response.BorrowingListResponse;
import id.co.libralink.borrowing.query.BorrowingQuery;
import id.co.libralink.borrowing.repository.BorrowingRepository;
import id.co.libralink.borrowing.service.BorrowingService;
import id.co.libralink.common.constant.CommonConstant;
import id.co.libralink.common.exception.BaseException;
import id.co.libralink.common.query.SearchQuery;
import id.co.libralink.common.util.DateTimeUtil;
import id.co.libralink.common.util.PaginationUtil;
import id.co.libralink.common.util.PreconditionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BookService bookService;

    private final UserService userService;

    private final BorrowingRepository borrowingRepository;

    private final BorrowingDetailMapper detailMapper = new BorrowingDetailMapper();

    private final BorrowingListMapper listMapper = new BorrowingListMapper();


    @Override
    public Page<BorrowingListResponse> getAllBorrowing(SearchBorrowingRequest request) {
        BorrowingQuery query = new BorrowingQuery()
                .userIdEquals(request.getUserId())
                .userNameContains(request.getUserName())
                .userEmailContains(request.getUserEmail())
                .bookTitleContains(request.getBookTitle())
                .bookAuthorContains(request.getBookAuthor())
                .bookPublisherContains(request.getBookPublisher())
                .borrowDateBetween(request.getBorrowDateStart(), request.getBorrowDateEnd())
                .dueDateBetween(request.getDueDateStart(), request.getDueDateEnd())
                .statusesIn(request.getStatuses());
        Page<Borrowing> page = findAll(query, request.getPageable());
        return PaginationUtil.map(page, listMapper::toTarget);
    }

    @Override
    public BorrowingDetailResponse getBorrowingById(Long id) {
        Borrowing entity = PreconditionUtil.assertNotNull(findById(id), Borrowing.class);
        return detailMapper.toTarget(entity);
    }

    @Override
    @Transactional
    public BorrowingDetailResponse borrowBook(BorrowBookRequest request) {
        User user = PreconditionUtil.assertNotNull(userService.findById(request.getUserId()), User.class);
        Book book = PreconditionUtil.assertNotNull(bookService.findById(request.getBookId()), Book.class);

        Optional<Borrowing> activeBorrowing = findActiveBorrowingByUser(user);
        if (activeBorrowing.isPresent()) {
            throw new BaseException("User already has active loan");
        }

        if (book.getAvailableStock() <= 0) {
            throw new BaseException("Book not available");
        }

        Borrowing entity = new Borrowing();
        entity.setUser(user);
        entity.setBook(book);
        entity.setStatus(BorrowStatus.BORROWED);

        Date now = new Date();
        Date dueDate = DateTimeUtil.addDays(now, request.getBorrowPeriod().getDays());
        entity.setBorrowDate(now);
        entity.setDueDate(dueDate);

        book.setAvailableStock(book.getAvailableStock() - 1);
        bookService.save(book);

        return detailMapper.toTarget(create(entity));
    }

    @Override
    @Transactional
    public BorrowingDetailResponse returnBook(Long borrowingId, Long userId) {
        Borrowing borrowing = PreconditionUtil.assertNotNull(findById(borrowingId), Borrowing.class);

        if (!borrowing.getUser().getId().equals(userId)) {
            throw new SecurityException("User is not the owner of this borrowing");
        }

        if (borrowing.getReturnDate() != null) {
            throw new BaseException("Book already returned");
        }

        borrowing.setReturnDate(new Date());
        borrowing.setStatus(BorrowStatus.RETURNED);

        Book book = borrowing.getBook();
        book.setAvailableStock(book.getAvailableStock() + 1);
        bookService.save(book);

        return detailMapper.toTarget(borrowingRepository.save(borrowing));
    }

    @Override
    public Page<Borrowing> findAll(SearchQuery<Borrowing> query, Pageable pageable) {
        return borrowingRepository.findAll(query, pageable);
    }

    @Override
    public Optional<Borrowing> findById(Long id) {
        return borrowingRepository.findById(id);
    }

    @Override
    public Borrowing create(Borrowing entity) {
        return borrowingRepository.save(entity);
    }

    @Override
    public Borrowing update(Borrowing oldEntity, Borrowing newEntity) {
        PreconditionUtil.assertNotNull(oldEntity, Borrowing.class);
        BeanUtils.copyProperties(newEntity, oldEntity, CommonConstant.DEFAULT_IGNORE_PROPERTIES);
        return borrowingRepository.save(oldEntity);
    }

    private Optional<Borrowing> findActiveBorrowingByUser(User user) {
        return borrowingRepository.findByUserAndReturnDateIsNull(user);
    }

}
