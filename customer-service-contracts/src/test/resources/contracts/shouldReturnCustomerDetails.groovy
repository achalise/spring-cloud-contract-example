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
