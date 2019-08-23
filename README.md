# Testing homework for Printify Public API
## Target
Create a small automation suite:
* In Java/Kotlin
* No Gerkin
* Runs tests in parallel
 
## Prerequisites
Create printify account:
   Go to Settings > Connections and generate an API token  
    _API docs: https://developers.printify.com/#products_

## Test Cases
1. User can create a product
2. User can update a product
3. User can delete a product

_Use `GET products` at least once to validate the result._

## Implementation
Implementation is made in Groovy language using Apache HTTP client, TestNG and Gradle build system.

_*NOTE: This implementation is a concept for such tests - in real world test suite author would implement several parts differently to account for scope increase, performance and targeted execution of selected set of tests.*_

## Running
At the root directory of the project run command `gradlew clean test` (independent from OS).  
Test report will be located at `build/reports/tests/test/index.html`