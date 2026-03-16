# Racing Metrics Dashboard

## 📖 Project Overview
**Racing Metrics** is a Java-based desktop application designed to track, analyze, and visualize comprehensive motorsport data. Built with JavaFX, the application provides an intuitive graphical interface for managing race entries, monitoring driver statistics, analyzing lap times, and optimizing race strategies like pit stops and tire usage. 

This project follows the **MVC (Model-View-Controller)** architecture, seamlessly separating the graphical user interface (FXML) from the backend Java business logic.

## 🛠️ Tech Stack
* **Programming Language**: Java
* **GUI Framework**: JavaFX (FXML for layout design, CSS for styling)
* **IDE/Environment**: Eclipse/IntelliJ IDEA (supports `.classpath` and `.project` configurations)

## ✨ Key Features
* **User Authentication**: Secure login system (`loginUI.fxml`) to access the dashboard.
* **Comprehensive Dashboard**: A central hub (`DashboardUI.fxml`) providing a quick overview of racing events and metrics.
* **Driver Profiles & Stats**: View detailed driver profiles and track their historical performance and statistics.
* **Race Management**: Enter new race data and view complete race results.
* **Telemetry & Strategy Analysis**:
  * **Lap Times**: Record and analyze lap-by-lap performance.
  * **Pit Stops**: Track pit stop durations and efficiency.
  * **Tire Management**: Monitor tire usage and receive dynamic tire recommendations based on race conditions.
* **Season Tracking**: View season-long summaries and current championship rankings.
* **Performance Reports**: Generate detailed performance reports for post-race analysis.

## 📂 Folder Structure

    📦 Racing_Metrics
     ┣ 📂 src
     ┃ ┣ 📂 UI                  # FXML layout files and CSS styling
     ┃ ┃ ┣ 📜 DashboardUI.fxml
     ┃ ┃ ┣ 📜 DriverProfileUI.fxml
     ┃ ┃ ┣ 📜 LapTimeUI.fxml
     ┃ ┃ ┣ 📜 PitStopUI.fxml
     ┃ ┃ ┣ 📜 TireRecommendationUI.fxml
     ┃ ┃ ┣ 📜 styles.css
     ┃ ┃ ┗ 📜 ... (other FXML views)
     ┃ ┣ 📂 controllers         # Java classes handling UI logic and events
     ┃ ┃ ┣ 📜 DashboardController.java
     ┃ ┃ ┣ 📜 DriverProfileController.java
     ┃ ┃ ┣ 📜 LapTimeController.java
     ┃ ┃ ┣ 📜 PitStopController.java
     ┃ ┃ ┗ 📜 ... (other controllers)
     ┃ ┗ 📂 main                # Application entry points and routing
     ┃   ┣ 📜 main.java         # Main launch class
     ┃   ┗ 📜 sceneManager.java # Utility to handle navigation between FXML scenes
     ┣ 📜 .classpath            # Eclipse classpath configuration
     ┣ 📜 .project              # Eclipse project configuration
     ┗ 📜 README.md             # Project documentation

## 🚀 Getting Started

### Prerequisites
Make sure you have the following installed on your local machine:
* Java Development Kit (JDK) (Version 11 or higher recommended)
* JavaFX SDK (if not included in your JDK)
* An IDE like IntelliJ IDEA, Eclipse, or VS Code configured for Java.

### Installation & Execution

1. **Clone the repository**
   
       git clone <repository-url>
       cd sda-project/Racing_Metrics
   

2. **Open in your IDE**
   * If using **Eclipse**, simply import the project as an existing Eclipse project (it will utilize the `.classpath` and `.project` files).
   * If using **IntelliJ IDEA**, open the `Racing_Metrics` folder and ensure your Project Structure has the JavaFX library added to your dependencies.

3. **Configure JavaFX (If required by your IDE)**
   Ensure you add the JavaFX modules to your VM options when running the project:
   
       --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
   

4. **Run the Application**
   Locate and run the `main.java` file located in `src/main/main.java`. This will launch the application starting with the Login or Dashboard screen.
