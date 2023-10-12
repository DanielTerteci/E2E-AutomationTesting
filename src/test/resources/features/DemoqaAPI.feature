Feature: DemoQA BookStore Application Api Testing

  @api1
  Scenario: API End-To-End Flow
    Given Create user account
    When Check the response code 201
    And Generate token for user
    Then Check the response code 200

    When Check that user is authorized
    Then Check the response code 200

    When Get all the books
    Then Check the response code 200

    When Add a random book from books list to the user profile
    Then Check the response code 201

    When Delete a book from the user profile
    Then Check the response code 204

    When Add a random book from books list to the user profile
    Then Check the response code 201

    When Put a book in the user profile
    Then Check the response code 200

    When Delete all books from the user profile
    Then Check the response code 204

    When Get a book from book list
    Then Check the response code 200

    When Get a user from database
    Then Check the response code 200

    When Delete user account
    Then Check the response code 204


# I've divided the end-to-end scenario into smaller scenarios
  @api
  Scenario: Delete a new added book from the profile
    Given Create user account
    When Generate token for user
    And Get all the books
    And Add a random book from books list to the user profile
    Then Delete a book from the user profile

  @api
  Scenario: Create and delete an account
    Given Create user account
    And Check the response code 201
    Then Delete user account

  @api
  Scenario: Add a random book in the profile and delete the account
    Given Create user account
    And Check the response code 201
    And  Get all the books
    And Add a random book from books list to the user profile
    Then Delete user account

  @api
  Scenario: Delete all books from profile and get the user from database, then delete the account
    Given Create user account
    When Check the response code 201
    And Get all the books
    And Add a random book from books list to the user profile
    And Delete all books from the user profile
    And Get a user from database
    Then Delete user account



