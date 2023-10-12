Feature: DemoQA BookStore Application

  @prio1
  Scenario: Delete a book from collection
    Given User navigates to Book Store page
    When User clicks on login button
    And Login Page is displayed
    Then Username and password are entered

    When Login is attempted
    Then Book Store page is displayed

    When User selects the book
    And The book is added to collection and the alert is closed
    Then User go to store

    When User navigates to profile
    And Book "Git Pocket Guide" is present
    Then Check the title,author and publisher for book

    When User wants to delete the book
    Then The book is deleted and the alert is closed

    When User navigates back to store
    Then User logs out


  @prio2
  Scenario: Unauthorized users cannot add books to car
    Given User navigates to Book Store page
    When User clicks on the book

    And The button to add a book in collection is not displayed
    Then User navigates back to book store

  @prio3
  Scenario: Add 10 books to collection and check page functionality
    Given User navigates to Book Store page
    When User clicks on login button
    And Login Page is displayed
    Then Username and password are entered

    When Login is attempted
    Then Book Store page is displayed

    When User adds all books to collection
    Then User navigates to profile

    When User clicks the next button
    Then User clicks on the previous page button

    When User delete all books
    Then User logs out

# I know this scenario shouldn't require manual intervention(recapcha) but i just wanted to have it covered
  @prio4
  Scenario: User create an account and delete it
    Given User navigates to Book Store page
    When User clicks on login button
    And Login Page is displayed
    Then User clicks create a new user

    When User check the fields and clicks register button
    Then User go back to login page

    When Login Page is displayed
    Then User log in with new account

    When Login is attempted
    Then Profile page is displayed

    When User navigates to profile
    And User use delete account button
    Then Login Page is displayed

  @prio5
  Scenario: Check that the book remains in profile collection after the user log out
    Given User navigates to Book Store page
    When User clicks on login button
    And Login Page is displayed
    Then Username and password are entered

    When Login is attempted
    Then Book Store page is displayed

    When User selects the book
    And The book is added to collection and the alert is closed
    Then User go to store

    When User navigates to profile
    And Book "Git Pocket Guide" is present
    Then User logs out

    When Login Page is displayed
    And Username and password are entered
    Then Login is attempted

    When User navigates to profile
    And Book "Git Pocket Guide" is present
    Then User wants to delete the book

    When The book is deleted and the alert is closed
    Then User logs out

#  @prio6
#  Scenario: User find a book and add it in cart
#    Given User navigates to Book Store page
#    When User clicks on login button
#    And Login Page is displayed
#    Then Username and password are entered

#    When Login is attempted
#    Then Book Store page is displayed

#    When User search the book "You Don't Know JS"
#    And User selects the book
#    Then The book is added to collection and the alert is closed

#    When User go to store
#    Then User navigates to profile

#    When User search the book "You Don't Know JS"
#    Then Book "You Don't Know JS" is present

#    When User wants to delete the book
#    And The book is deleted and the alert is closed
#    Then User logs out

#  @prio7
#  Scenario: User is redirected to view profile from login page
#    Given User navigates to Book Store page
#    When User clicks on login button
#    And Login Page is displayed
#    Then Username and password are entered

#    When Login is attempted
#    Then Book Store page is displayed

#    When User go to login page
#    Then User view his profile from login page
#    And User is redirected to profile page


