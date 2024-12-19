# CSCI3170 project
## Group Information
**Group Number**: 14

### Group Members:
| Name | Student ID |
|-----------------------|--------------|
| Chan Tak Hong | 1155194240 |
| Leung Chi Hin | 1155177233 |
| Cheng Wai Man | 1155192172 |



## List of Files with Description

### **Source Code**
1. **`main.java`**
   - The main entry point of the program.
   - Handles the connection to the Oracle database and provides the main menu for users to navigate between the Administrator, Salesperson and Manager operations.

2. **`subSection/Administrator.java`**
 

3. **`subSection/Salesperson.java`**
   - Contains the logic for Salesperson operations.
   - Includes functionality for searching for parts and performing transaction.

4. **`subSection/Manager.java`**

5. **`subSection/Utility.java`**
   - Provides utility methods for common operations, such as prompting the user for input and printing query results.

6. **`lib/*`**
   - Contains ojdbc10.jar for compilation of the program
---
## Methods of Compilation and Execution

### **Compilation**
1. Ensure you have the required environment set up:
   - **Java Development Kit (JDK)** installed (version 8 or above).
   - The `ojdbc10.jar` file should be present in the `lib\` directory.

2. Compile the Java files:
   - Navigate to the root directory of the project.
   - Run the following command to compile all `.java` files:
     ```bash
     javac -cp .;lib\ojdbc10.jar main.java subSection/*.java
     ```

   - Use `:` instead of `;` as the classpath separator if using macOS or Linux:
     ```bash
     javac -cp .:lib/ojdbc10.jar main.java subSection/*.java
     ```

### **Execution**
1. Run the program:
   - Execute the compiled program by running the following command:
     ```bash
     java -cp .;lib\ojdbc10.jar main
     ```

   - **Note**: Use `:` instead of `;` as the classpath separator if using macOS or Linux:
     ```bash
     java -cp .:lib/ojdbc10.jar main
     ```

2. Follow the prompts in the terminal:
   - Choose options from the main menu to access Administrator, Salesperson or Manager operations.
   - Provide inputs as requested by the program.
   - To terminate the program, input 4 in the main menu
---
