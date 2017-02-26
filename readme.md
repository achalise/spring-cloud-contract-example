### Spring Cloud Contract Example

Spring Cloud Verify project enables writing contracts as a swoftware which can then be managed
in version control system. It enables consumers to wtite contracts, create a pull request so that
api developers can review and implement those contracts. It generates tests from the 
contract definitions ensuring consumers and producers of the service all honour the contract
at all times. This example illustrates how that can be achieved using Spring Cloud Verify libraries.
Lets assume a client needs to retrieve details of a customer by their id. In the following steps, we will create 
contract definition as needed by the client and demonstrate workflow that allows contract driven development
by both client and service provider.

## Contract Definitions

Contracts are defined in a separate project. We have defined contracts in the project customer-service-contracts.
Following key points in the gradle build file ensure that we have all dependencies needed in place.

 `classpath "org.springframework.cloud:spring-cloud-contract-gradle-plugin:${verifierVersion}"
 
 Then apply the following plugins so that contracts are bundled into a stub and published in the repository as 
 part of the build process.
 
```` 
apply plugin: 'spring-cloud-contract'
apply plugin: 'maven-publish'

````

Now define contracts in the package ``src/test/resources/contracts``. We have defined following contract in the file
``shouldReturnCustomerDetails.groovy``

````
package contracts


org.springframework.cloud.contract.spec.Contract.make {
    request {
        name "Should_Return_Customer_Details_For_Id"
        method GET()
        urlPath('/customer/123432')
    }
    response {
        status 200
        body(
                customerId : "123432",
                firstName: "Joe",
                lastName: "Blog",
                mobileNo : "0412345345",
                address : [
                        street: "20 Oxford St",
                        suburb: "Surry Hills",
                        state: "NSW",
                        postcode: "2000",
                        country: "Australia"
                ]
        )
        headers {
            contentType("application/json")
        }
    }
}


````

Now executing command `` gradle build -x test``, generates client stubs without executing the tests, because we do not
implement tests here, as that would be implemented by service producer when they declare they are implementing these 
contracts by including them in their project dependency, which we will get into detail later when we talk about the
workflow followed by service developers.

````

aa932336:customer-service-contracts achalise$ gradle build -x test
:compileJava
:compileGroovy UP-TO-DATE
:processResources
:classes
:findMainClass
:jar
:copyContracts
:generateClientStubs
:verifierStubsJar
:bootRepackage
:assemble
:generateContractTests
:check
:build

BUILD SUCCESSFUL

Total time: 1.69 secs

````
