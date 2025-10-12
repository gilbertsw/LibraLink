# Class Management Standard
This document outlines best practices and guidelines that should be followed when designing and organizing classes in LibraLink backend API


### Content

1. [General](#general)
2. [Entity](#entity)
3. [Service](#service)
4. [Utility](#utility)
5. [Base](#base)
6. [Enum](#enum)
7. [Dto](#dto)


# General

### Naming
Class name consists of a feature object and the technological component of that class:

**Formula:**
```
<Object><Component>
```

**Example:**
```
EmployeeController
UserRepository
UserDetailService
```

Here are some generic rules of class naming:
1. Class name format should follow [Java standard](https://www.oracle.com/technetwork/java/javase/documentation/codeconventions-135099.html).
2. Class name can contain more than 2 words, but keep the character count low.
3. Class name can contain number, but it should not be in front of the name.
4. Do not abbreviate words in the class name (both object name and component name). Some exceptions apply for this rule.

### Placement
Class should go to their own respective feature according to their business domain. Where the class is frequently used by other classes, that is the feature package where it belongs. After deciding which feature a class go to, put the class in its respective layer.

Destination package of a class can be determined by its name itself. Object name will determine its feature, and component name will determine its layer.

**Example**

`BookController` goes to `id.co.libralink.book.controller`

`BorrowingServiceImpl` goes to `id.co.libralink.borrowing.service.impl`

**Notes**
In the next section, there will be a few breakdowns of specific classes. If any class is listed below, then it follows special rules that are different than generic rule. However, if a certain class is not listed below, use general rules for naming and placement.


# Entity
Entity class is the object class that is tied to database table.

### Naming
Name of entity class should be the table name in Java standard format (Cap words, no underscore), in its singular form.

**Example**

| Table | Class |
| ------ | ------ |
| employees | Employee |
| users | user |
| user_details | userDetail |

### Placement
Entity class should be placed inside `model` layer package in its respective feature package.

**Example**

`Book` entity goes to `id.co.libralink.book.model`

`Borrowing` entity goes to `id.co.libralink.borrowing.model`


# Service
Service contains application business logic. It consists of interface and implementation class. The interface part of service is considered as "contract" for other component. The implementation part of service is where the main business logic resides.

### Naming
Service interface should be named as the entity primarily involved in the service, followed by `Service` as the suffix. Service implementation class should be named as the interface name, then followed by `Impl` as the suffix.

**Examples**
```
BookService
BookServiceImpl
BorrowingService
BorrowingServiceImpl
```

### Placement
Service interface and implementation class should go to `service` layer package inside their own respective feature package. No need to put the implementation classes inside a sublayer package such as `impl` package, check next section to see explanation.

**Examples**
```
id.co.libralink.book.service.BookService
id.co.libralink.borrowing.service.BorrowingServiceImpl
```

### Encapsulation
Service implementation class should not be set `public`. It is better to leave the implementation class as `package-private`. Remove the encapsulation modifier in front of the `class` keyword when defining an implementation class, to set it as `package-private` class. On the other hand, service interface should be kept to `public` as it is going to be used in any classes outside this package.

**Example of public class**
```
public class BookServiceImpl implements BookService {
    ...
}
```

**Example of package private class**
```
class BookServiceImpl implements BookService {
    ...
}
```

Service implementation class should not be public because other classes should not import / use / call directly to its implementation. Other class that needs to access this particular service should use the interface instead (implementation choice is handled by Spring autowiring) and call the needed method from there.
Service implementation class is only imported by their own service interface. Since both of the service interface and implementation class are in one same package, it is safe to leave the implementation as package private.

However, there are some exception to this situation:
- The service is part of configuration or autoconfiguration, configuration class is in another package
- The author intentionally make it public, so it can be extended by another class by another person

These are just few examples of the exception. Bottom line, use package private as much as possible, but feel free to set it to public if necessary.


# Utility
Utility class is a class that contains only static method. Usually utility class methods are used by several classes (often service) because they share common usage.

The main difference between method inside utility class and service is, methods in utility class do not require access to services and repositories, while methods in service require access to other services and repositories. Therefore, there's no autowiring at all in utility class.

### Naming
Utility class should be named by the object it most interacted with, then followed by `Utils` as the suffix. Sometimes, if the utility is used in a whole feature, it could use same name as the feature.

**Examples**
```
id.co.libralink.technician.util.TechnicianUtils
```

### Background
Utility class come from common method usage between classes, features, or even projects. Creation of utility class originally came from copy pasting a certain method. Here's the background story of how the utility methods evolved:
1. Certain service method has an instruction set that modify a certain object.
2. Other service method requires the same instruction set, it could be copy-pasted from the previous method
    * Potential refactor to private method, move the instruction set and create a private method, use it in both methods that require it.
3. Other service class in the same feature requires the same private method, the private method could be copy-pasted from the previous service class.
    * Potential refactor to utility class, move the private method and create a utility class, use it in both services that require it.
4. Other feature needs the same utility method, no need to copy-paste because utility methods already public.
    * Potential movement of utility class to `common.util` if there are 2 features using it.
5. Other platform needs the same utility class, the utility class is copy-pasted from existing platform.
    * Potential movement of utility class to `common` project.
      While creating utility methods (starting from common private method), be aware of this evolution and refactor accordingly.

### Placement
Utility class should be placed under `util` package. As for which feature it should reside, it depends on these rules:
1. If the utility class methods are used by 1 feature only, put it under the feature package that was using it.
2. If the utility class methods are used in 2 or more features, put it under `common.util` package in the project.
3. If the utility class methods are used in more than 1 projects (usually marked with copy pasting the util from one project to another), than it is time to move the method to `common` project.

### Constructor and Modifier
According to lint warning, utility class should follow these 2 rules:
1. Put `final` modifier in the definition of class.
2. Create an empty `private` constructor in the class, and put `// can't instantiate` comment inside the constructor.
3. Methods inside utility class can be `private` (for set of instructions that is used in several methods) and `public`, but all of them should be `static`.

This is because utility class should not be inherited, and the method inside should not be overridden. If there are any need to inherit or override the method to create similar methods, it is best to put the method in the current utility class instead.

**Example**
```
public final class PdfUtil {
    
    private PdfUtil() {
        // can't instantiate
    }
    
    public static byte[] generatePdfInBytes(String html) {
        validateHtml(html);
        ...
    }
    
    public static Pdf generatePdf(String html) {
        validateHtml(html);
        ...
    }
    
    private static void validateHtml(String html) {
        ...
    }
}
```


# Base
Base class is a class (usually entity class) that contains some fields or methods that are common to the subclass.

### Naming
Base class should be named like an entity class, with `Base` in front as the prefix.

**Example**
```
BaseEntity
BaseService
```

### Placement
Base class is usually used (in import) by the entity class in the same feature. Therefore, it is best to put base class at the same package as the entity class. This way, base class can be encapsulated to `package-private`. However, for base class that is used in several features, follow placement rule of [utility class](#utility).

**Example**
```
id.co.libralink.common.master.model.entity.base.BaseCustomer
id.co.libralink.user.model.entity.base.BaseUser
```

### Encapsulation
Encapsulation of base class depends on the usage. If base class is used by the entity classes in the same features, it can be set to `package-private`. Other than that, it should be `public`.

However, base class can be abstract most of the time. Make sure that base class is never instantiated, and set it as abstract.

**Example**
```
@Getter
@Setter
@MappedSuperclass
abstract class BaseEntity {
    ...
}
```


# Enum
Enum is an enumeration class in Java, usually marked with the keyword `enum` in the class declaration. Enum is used to create a static type that can be use throughout all application.

### Naming
Name of enum class is similar to entity, no need to add suffix on the name. Most enum class name ends up with `Status` or `Type` as the suffix, but it is not mandatory. It all depends on the feature requirement itself.

**Example**
```
OperationType
BookStatus

```

### Placement
Enum class should be placed inside `enums` package, while the `enums` package itself can be considered as layer package and should be put right under the feature / subfeature package. Enum is used in several place like entity and service because of business logic, so no need to put it as a sublayer under `model` package.

**Example**

`BookStatus` enum goes to `id.co.libralink.book.enums`

`OperationType` enum goes to `id.co.libralink.common.enums`


# Request and Response
Request and Response are classes similar to entity but without `@Entity` annotation. It works similar as entity object, but without creating the table (so no JPA annotations and repository). The main usage of this class is for a container to carry objects, and can be used for several usages such as:
- Send the object to front-end.
- Create custom made reports.
- Encapsulate response from third-party APIs
- Property class to help refactor method with a lot of parameters.

### Naming
Naming of Request and Response classes is similar to entity class, but with `Request` or `Response` as the suffix. It is best to avoid naming VO with same name as the entity class. As for the object name itself, choose the object name that reflects what it contains, or just match it with the requirement (such as report type or any of the specification).

**Examples**
```
LoginRequest
BookRequest
BorrowingResponse

```

### Placement
DTO class works similar as entity class, so it can be placed under `model` layer package. However, if the requirement is met, it can be placed in sublayer package.

**Examples**

`BookRequest` goes to `id.co.libralink.book.model.request`

`BorrowingResponse` goes to `id.co.libralink.borrowing.model.response`
