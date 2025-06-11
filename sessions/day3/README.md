# 07-06-25

### XML

extensible markup language defines a set of rules that allow you to create your own user defined markup language. Since their are no predefined tags in xml, the dev must define the rules for its own markup language. These rule identify various factors such as what is the root element, what are the child elements, the occurance of the child element, attributes pertaining to the child element and the nesting of these child element.</br>
These specifications are defined using either a `DTD` or `XSchema`. Every xml doc should start with a prolog and usually will be associated either with DTD or a schema. This enables the xml parser to check the validity of the xml document.

### Well-formed document

if an xml document has
a. prolog
b. root element
c. is properly nested
d. all tags opened are closed
e. tags are in proper case
f. attributes are quoted

then the xml document is said to be a wellformed xml document

### Valid document

In order for any xml document to be a valid document it must first be a wellformed document
all the rules defined with the associated DTD or schema should be followed
if both the criterias are met the document is said to be a valid document.

1. `<?xml version="7.0"?>` -> known as prolog
2. defining format or a specific document. </br>
    `DTD` -> document type description</br>
    `XSchema` -> xml schema
3. `pom.xml` is a configuration file with predefined format defined by maven.

### `GET/` limitations

1. restricted data transfer size.
2. exposed data in url.
3. only text data can be transfered.

### Communicating to servlet

1. URL rewriting -> can only be possible from between current and next url.
2. sessions -> if session is over data is over.
3. applicationContext(`servletContext` in terms of servlets)  -> data is in global context.

### DAO

Since servlets shouldn't have direct dependency for communication with the database we need to implement `DAO` design pattern.

### Spill overs

1. admin home
2. add category -> selection is dynamic so use servlet, use of form.
3. add product
4. update category
5. update product