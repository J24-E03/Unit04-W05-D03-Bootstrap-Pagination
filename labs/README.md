# **Lab: Add Bootstrap Styling and Pagination to Your Library App**

### **Learning Objectives**

By the end of this lab, you will be able to:

* Apply Bootstrap 5 for responsive styling and layout improvements.
* Style your forms, tables, and navigation using Bootstrap components.
* Add pagination to your book listing page using Spring Data.
* Enhance user experience through a cleaner UI and better usability.

---

## **Part 1: Add Bootstrap Styling**

1. **Include Bootstrap in Your Project**

   * Update your base HTML layout to load Bootstrap’s CSS and JS from a CDN.

2. **Style the Layout**

   * Use a container element to wrap your content and apply margin/padding.
   * Add a consistent navigation bar to the top of your site.
   * Use grid layout to organize different sections if necessary.

3. **Style Forms and Tables**

   * Apply appropriate Bootstrap classes to your form elements for spacing and alignment.
   * Style your tables to make the book listings easier to read.
   * Improve the appearance of buttons and labels using Bootstrap's utility classes.

4. **Refactor Other Pages**

   * Apply consistent styling across all other views including detail pages, add/edit forms, and search pages.

---

## **Part 2: Add Pagination to the Book List**

1. **Update Repository Support**

   * Ensure your book repository supports pagination by extending the appropriate Spring Data interface.

2. **Update the Service Layer**

   * Modify your method that fetches filtered books to accept pagination parameters.
   * Return a paginated result instead of a simple list.

3. **Update the Controller**

   * Update your endpoint to accept a `page` parameter.
   * Pass the page information to the service layer and then to the view.

4. **Update the Thymeleaf View**

   * Modify your list view to show only a subset of books per page.
   * Add pagination controls that allow users to navigate through pages.
   * Ensure that when filters are applied, pagination links maintain those filters.

