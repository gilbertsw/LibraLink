# Package Management Standard
LibraLink backend API is using package by features concept as a package management standard. Inside each feature, classes are organized by technological layers for app.

```
<domain.appname>.<module>
    .<feature>
        .<subfeature> (optional)
            .<layer>
                .<sublayer> (optional)
```

Example:
```
id.co.libralink.apigateway.account.model.entity
```

### Content

1. [General](#general)
2. [Module](#module)
3. [Feature](#feature)
4. [Subfeature](#subfeature)
5. [Layer](#layer)
6. [Sublayer](#sublayer)
7. [External](#external)
8. [Other](#other)


### General
Here is some general standard on package:
1. Package name must be lower-case, regardless of number of words it contains. (according to [java standards](https://www.oracle.com/technetwork/java/javase/documentation/codeconventions-135099.html))
2. Package name can contain numbers, but it should not be in front of the name.
3. Package name cannot contain special characters, even if it's just underscore (`_`) or dash (`-`).
4. Package name must be in singular form.
5. Package name should contain no more than 2 words.


### Module
Module commonly used in microservice. It is basically the microservice itself and should be named after the module.

However, since this API is designed using a monolithic architecture, there is only one main module, which is `.libralink`.


### Feature
Feature is a business domain inside a module. One feature may contains multiple entities and services that interact with each other frequently. One feature is usually represented as a menu item in the main menu of the module.

Feature package should be named as the feature name.

**Example:**
```
id.co.libralink.apigateway
id.co.libralink.book
id.co.libralink.common
```


### Subfeature
Subfeature is a part of a feature. Generally, feature may contains multiple entities and services that interact with each other frequently. However, if the number of entities and services (presumably with same prefix) appears to be flooding the feature, it could be a candidate of a subfeature.

A small business domain inside a major domain can be called a subfeature as well. Usually the major domain will need to communicate to the smaller domain, but not as frequent as the classes inside the major domain itself.

Another way to identify a subfeature is to look at the submenu inside a feature. One subfeature is usually represented as one submenu item.

Create a subfeature package if the entities, services, and any other related classes (with the same prefix) count exceed 15 files. It should be named after the common prefix.

Be aware that if the subfeature contains a lot of files (over 40 files), it could be a potential feature. Movement from subfeature to feature level can be considered. However, this is all depends on the implementor and the requirement itself.

Subfeature package should be named as the subfeature name, preferably no longer than 2 words. No need to incorporate the parent feature name in the package name.

**Example:**
```
id.co.libralink.dashboard.bookcomplaint
id.co.libralink.dashboard.bookorder
```

**Notes:**
If the feature package contains subfeature package(s), it is okay to leave the classes of the main feature in its feature package. No need to create subpackage just to contain the classes of the main feature.


### Layer
Layer is technological component part of a feature / subfeature. These are some of the stuffs that are considered as layer:
- Controller
- Service
- Repository
- Util
- Query
- Enums
- Scheduler
- Model
- etc.

This list are not exclusive. Some technological layer that are not listed here maybe considered as layer and can be consolidated into one layer package.

Layer package should be named as the technological layer name, preferably no more than 2 words. Usually layer name will have the same name as the suffix of the majority of the classes inside that package.

**Example:**
```
id.co.libralink.book.service
id.co.libralink.book.controller
```


### Sublayer
Sublayer is a part of technological layer. Sublayer is used solely for organization, if there's too much same component classes in a certain layer. Sublayer should be named as the technological sublayer name, preferably no more than 2 words. Similar to layer, sublayer will have the same name as the majority of the contents.

Create a sublayer package if inside a layer, there's other classes with different suffix, and their count exceed 15 files.

**Example:**
```
id.co.libralink.book.stockadjustment.specification.executor
```


### External
External package is a package that contains feature files from third-party APIs. It may contain replicated entity, business logic, and API call services.

External package should be named as `external` and packages inside of `external` package should follow the rule starting from `feature` package.

**Example**
```
id.co.libralink.external.xendit
```


### Other
In some cases there's always some stuffs (packages and classes) that don't belong to any of the features. These generic stuffs should go to `common` package. These are the stuffs, but not exclusive, that should go to `common` package:
- Config (Spring Application Configuration and any other configurations)
- Util (generic utility classes)
- Base (base classes that are used by features across the platform)
- etc.
