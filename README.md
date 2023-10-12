# project-daniel-terteci

    Bugs and mistakes i've discovered:
    1. If we run the tests on a smaller screen, the adds that appear are put over the buttons and doesn't work even if we scroll
    2. On the book page(Where we have the details about the book), the 2 buttons have the same id/ xpath /css
    3. In the smaller window(mobile or if we minimize the window), the 2 buttons disappear (so i set the browser to start in fullscreen)
    4. The search bar button cannot be pressed 
    5. When we have more books in the profile collection, the image of the books changes
    6. On the profile page, if we select to display the books in several rows, the buttons on the page disappear
    7. The 3 buttons(logout, delete account and delete all books) on the profile page have the same ID
    8. The "OK" button from the "delete account" button and "delete all books" button have the same ID and the same class and it cannot be automatized to confirm when we press delete all books
    9. All the buttons (if we have more books in collection) from the action column to delete the book have the same id

    For the UI tests I set a profile, to start the driver only for these tests, not for the API tests.
    In the automatized tests,only on chrome and edge, on handleAlert function, alerts are closed before the code reaches "accept.alert". (firefox works normally)
    On login page, the username and password are case sensitive. (for example, we can introduce "d" instead of "D")