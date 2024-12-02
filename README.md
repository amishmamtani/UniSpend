# **UniSpend**

- **Contributors**: Amish Mamtani, Anish Pai, Olive Wachira, Thiyaan Karunanayake

## **Project Summary**

The **Unispend Application** is a personal finance tool specifically designed for college students in metropolitan cities to help them manage their finances. The application enables users to track their income, spending, and savings, offering features to create and monitor budgets, compare allocated versus actual spending, and make informed financial decisions. It also includes a chatbot to assist users with financial queries, ensuring that students have access to tailored guidance when needed.

### **Why This Project Was Made**

Unispend was created to address the unique financial challenges faced by college students in metropolitan areas. With the rising cost of living and the need for budgeting, the application provides a simple and intuitive platform to input income, categorize expenses, and visualize spending patterns. The goal of this project is to promote financial literacy among students, helping them make more informed decisions about their finances and avoid common budgeting pitfalls that can impact their ability to save and manage their finances effectively. 

---

## **Table of Contents**
- [Features](#features)
- [Installation Instructions](#installation-instructions)
- [Usage Guide](#usage-guide)
- [License](#license)
- [Feedback](#feedback)
- [Contributions](#contributions)

---

## **Features**
- **Budget Creation**: Easily create a budget by entering your income and allocating funds to various categories (e.g., groceries, entertainment).
- **Budget Tracking**: Track your spending in each category and compare it with your allocated budget.
- **Financial ChatBot**: Ask financial questions and receive tailored assistance from the built-in chatbot.
- **Budget Comparison**: View detailed comparisons between advised and actual spending to help identify areas for improvement.
- **Charts and Visuals**: Visualize your budget data through pie charts and other graphical representations.

---

## **Installation Instructions**

### **Requirements**
- Java 8 or higher
- Maven (for managing dependencies)
- A compatible IDE (e.g., IntelliJ IDEA, Eclipse)

### **Steps to Install**
1. Clone the repository to your local machine:
   ```bash
   git clone [https://github.com/amishmamtani/UniSpend.git]
   ```
   
2. Navigate to the project directory:
   ```bash
   cd budget-tracker
   ```

3. Install the required dependencies using Maven:
   ```bash
   mvn install
   ```

4. Ensure you have the necessary database set up for user management. (MongoDB or another database may be required for production systems.)

5. Run the application:
   ```bash
   mvn exec:java
   ```

### **Common Installation Issues**
- **Maven Dependency Issues**: Ensure you have Maven properly installed. If Maven dependencies are not being resolved, try running:
   ```bash
   mvn clean install
   ```
- **Database Configuration**: Make sure your MongoDB (or equivalent) instance is correctly configured. Check the database connection settings in `src/main/resources/application.properties`.

---

## **Usage Guide**

Once installed, you can start using the **Budget Tracker Application** by:

1. **Creating a Budget**: 
   - Enter your total income and specify categories for your budget (e.g., food, rent, entertainment).
   - Allocate amounts to each category.
   
2. **Tracking Expenses**: 
   - As you spend, log your expenses in their corresponding categories.
   - The app will update your spending balance automatically.
     
3. **Interacting with the ChatBot**:
   - Ask the chatbot financial questions, like "How much did I spend on groceries last month?".
   - Get immediate feedback or tips based on your spending habits.
   
4. **Viewing Reports & Comparing Budgets**:
   - Visualize your budget status using pie charts and tables.
   - Compare your advised budget against actual spending to see if you're staying on track.



---

## **License**

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## **Feedback**

We welcome feedback on how to improve this project! Please submit your suggestions through the following:

- [Google Form for Feedback]([https://link-to-feedback-form](https://forms.gle/cDZdLdukmJysYHoq9))

**Guidelines for feedback:**
- Provide clear, actionable suggestions.
- Focus on improving the user experience or adding meaningful features.
- Be respectful and constructive.

---

## **Contributions**

We encourage contributions to enhance this project. Here's how you can contribute:

1. **Fork the repository**:
   - Click the "Fork" button at the top of this page to create your own copy of the repository.

2. **Create a new feature branch**:
   ```bash
   git checkout -b feature-branch
   ```

3. **Commit your changes**:
   ```bash
   git commit -am 'Add new feature'
   ```

4. **Push to the branch**:
   ```bash
   git push origin feature-branch
   ```

5. **Create a pull request**:
   - Open a pull request to merge your feature branch into the `main` branch.

**Guidelines for Contributions:**
- Provide detailed explanations for your changes in the pull request.
- Ensure that your code follows the project's style guidelines.
- Make sure all tests pass before submitting a pull request.

---

### **Writing Criteria**
- All information is accurate and clear.
- The README is kept up-to-date as the project evolves.
- Technical jargon is minimized, explained when necessary, and used only when appropriate.

### **Visual and Technical Criteria**
- The README is written in Markdown format (`README.md`).
- Text is legible, with consistent formatting for headings, code snippets, and links.
- Screenshots or diagrams are provided where helpful, and they are non-distracting.
