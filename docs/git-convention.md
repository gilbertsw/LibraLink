# Git Convention Standard
LibraLink backend API is using custom simplified git convention standard from this [article](https://dev.to/varbsan/a-simplified-convention-for-naming-branches-and-commits-in-git-il4). 

### Content

1. [Branch](#branch)
2. [Commit](#commit)

### Branch

#### 1. Category
A git branch should start with a category. Pick one of these: `feature`, `bugfix`, `hotfix`, `test`, or `docs`.

<table>
  <thead>
    <tr>
      <th>Category</th>
      <th>Base</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>feature</td>
      <td>development</td>
      <td>Adding, refactoring, or removing a feature.</td>
    </tr>
    <tr>
    <tr>
      <td>bugfix</td>
      <td>development</td>
      <td>Fixing a bug.</td>
    </tr>
    <tr>
      <td>hotfix</td>
      <td>main</td>
      <td>Changing code with a temporary solution and/or without following the usual process (usually because of an emergency)</td>
    </tr>
    <tr>
      <td>test</td>
      <td>development</td>
      <td>Experimenting outside an issue or main task</td>
    </tr>
    <tr>
      <td>docs</td>
      <td>staging</td>
      <td>Creating new readme/documentation file or do some changes to an existing document.</td>
    </tr>
  </tbody>
</table>

#### 2. Reference
After the category, there should be a **slash** (`/`) followed by the reference of the task/issue you are working on.
It can be your task name, feature name, or issue number. If there's no reference, just add `no-ref`.

#### 3. Description
After the reference, there should be another **slash** (`/`) followed by a description which sums up the purpose of this specific branch. This description should be **short** and using **"kebab-case"**.

By default, you can use the title of the task/issue you are working on. Just replace any special character with **hyphen** (`-`).

**TL;DR: follow this pattern when branching:**
``
category/reference/description-in-kebab-case
``

**Example:**
- `feature/book/create-update-book-api`
- `bugfix/user/fix-email-regex-validation`
- `hotfix/issue-52/book-list-not-working`
- `test/no-ref/refactor-borrowing-fetch-strategy`
- `doc/no-ref/update-readme-document`


### Commit

#### 1. Category
A commit message should start with a category of change. You can pretty much use the following 4 categories for everything: `feat`, `fix`, `refactor`, and `chore`.

<table>
  <thead>
    <tr>
      <th>Category</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>feat</td>
      <td>Adding a new feature.</td>
    </tr>
    <tr>
    <tr>
      <td>fix</td>
      <td>Fixing a bug.</td>
    </tr>
    <tr>
      <td>refactor</td>
      <td>Changing code for performance or convenience purpose (e.g. readability)</td>
    </tr>
    <tr>
      <td>chore</td>
      <td>Everything else (writing documentation, formatting, adding tests, cleaning useless code, etc.)</td>
    </tr>
  </tbody>
</table>

After the category, there should be a **colon** (`:`) announcing the commit description.


#### 2. Statement(s)
After the colon, the commit description should consist in short statements describing the changes.

Each statement should start with a verb conjugated in an imperative way. Statements should be seperated from themselves with a **semicolon** (`;`).

**TL;DR: follow this pattern when committing:**
``
category: statement 1; statement 2
``

**Example:**
- `feat: add finish user template; add redirect button to forget password template`
- `fix: change email validation for non alphanumeric case`
- `refactor: rewrite create book`
- `chore: add git-convention documentation`