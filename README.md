# README.md

# Dimensional Inspection System

## Introduction

The Dimensional Inspection System is an advanced, automated quality control solution designed for the rail manufacturing industry. It leverages image processing techniques to detect dimensional deviations and defects in rail components, ensuring compliance with stringent quality benchmarks.

Automation in quality control enhances efficiency, reduces human error, and ensures consistent inspection standards, making it a crucial component in modern manufacturing processes.

This document provides an overview of the system's capabilities, setup requirements, operational principles, and potential enhancements, making it a valuable resource for engineers, quality control personnel, and developers.
The Dimensional Inspection System is an advanced, automated quality control solution designed for the rail manufacturing industry.
It leverages image processing techniques to detect dimensional deviations and defects in rail components, ensuring compliance with stringent quality benchmarks.

This document provides an overview of the system's capabilities, setup requirements, operational principles, and potential enhancements, making it a valuable resource for engineers, quality control personnel, and developers.

## Purpose and Functionality

### Objective
The system automates the inspection of rail components by detecting dimensional defects and deviations, ensuring that only high-quality rails proceed through production.

### Technologies Used

- **Python**: Core programming language
- **OpenCV**: Image processing
- **PIL (Pillow)**: Image comparison
- **NumPy**: Mathematical operations
- **MongoDB**: Stores camera and rail inspection data
- **MySQL**: Stores dimensional inspection results
- **Logging**: System monitoring and debugging
- **Threading**: Optimized data processing

### Core Functionalities

- **Rail ID Input**: Reads rail identifiers from a CSV file (`D:/railid/railid.csv`) to initiate inspections.
- **Image Data Retrieval**: Pulls image paths and metadata from MongoDB for processing.
- **Image Analysis**: Uses OpenCV-based image processing (e.g., edge detection, pixel matching) to detect anomalies.
- **Result Logging**: Stores inspection outcomes in a MySQL database for reporting and analysis.
- **Continuous Operation**: Processes new rail IDs automatically, ensuring minimal downtime and real-time tracking.

 ### Use Cases
-**1. Quality Control in Rail Manufacturing**
Ensures uniform inspection across all rails.
Reduces human error in quality assessment.

**2. Real-Time Defect Monitoring**
Immediate detection and classification of defects.
Supports decision-making for production adjustments.
**3. Automated Compliance Audits**

Ensures all manufactured rails meet industry standards.
Facilitates detailed reporting for regulatory compliance.

## Configuration and Setup

### Database Setup

#### MongoDB
- **Connection**: `mongodb://localhost:27017/`
- **Database**: `Rail`
- **Collection**: `camera`
- **Purpose**: Stores image metadata, including file paths, camera IDs, and processing status (`DD_inference` flag).

#### MySQL
- **Connection**: `mysql.connector.connect(host="localhost", user="root", password="ritesai", database="rites")`
- **Table**: `Dimensional_Inspection`
- **Purpose**: Stores structured inspection results (e.g., rail ID, detected defects, defect types, image differences).

### Setup Requirements

- **Hardware**:
  - High-resolution cameras for capturing rail images.
  - A dedicated server or workstation for processing data.

- **Software & Dependencies**:
  - Python environment with libraries: OpenCV, NumPy, PIL, pymongo, mysql-connector-python, and pixelmatch.
  - MongoDB and MySQL databases for data storage and retrieval.

- **Configuration**:
  - Proper database setup with predefined schemas.
  - A CSV file (`D:/railid/railid.csv`) containing valid rail IDs for inspection.

## Key Features

This system offers a competitive edge by combining advanced image processing with efficient database management, ensuring rapid and accurate defect detection. 
Compared to traditional methods, it reduces manual errors, enhances inspection consistency, and provides real-time monitoring for improved decision-making.

- **Automated Defect Detection**: Uses advanced image processing techniques to detect, classify, and log rail defects.
- **Continuous Monitoring**: Processes rail IDs in real-time, ensuring ongoing quality control.
- **Multi-Camera Support**: Handles data from multiple cameras, each assigned unique defect categories.
- **Database Integration**: MongoDB for image metadata, MySQL for structured storage.
- **Threaded Updates**: Utilizes multithreading to asynchronously update MongoDB status, improving performance.
- **Customizable Defect Classification**: Classifies defects based on camera ID, improving specificity and accuracy.

## System Architecture

### Overview
The system follows a modular architecture:
- **Input Layer**: Reads rail IDs from CSV, fetches image data from MongoDB.
- **Processing Layer**: Analyzes images using OpenCV, PIL, and NumPy to calculate differences and defect metrics.
- **Storage Layer**: Stores results into MySQL and updates processing status in MongoDB.
- **Control Layer**: Manages continuous operation and handles threading.

### Component Analysis
- **Image Acquisition**: Multi-camera setup to capture rail images from different angles.
- **Preprocessing**: Image resizing, noise reduction, and edge enhancement using OpenCV.
- **Defect Detection**: Uses MSE, pixel matching, and thresholding to identify defects.
- **Database Integration**: MongoDB stores image metadata, and MySQL manages structured results.
- **Performance Optimization**: Multi-threading ensures efficient processing of large datasets.
- **Logging & Monitoring**: Captures processing logs for debugging and quality assurance.

## Techniques Used

### Image Processing Techniques
- **Edge Detection**: Enhances edges to identify defects.
- **Pixel Matching**: Compares pixels between reference and inspected images.
- **Thresholding**: Segments images for better defect visibility.
- **Morphological Operations**: Cleans up noise and enhances image features.
- **Mean Squared Error (MSE) Calculation**: Quantifies differences between two images.
- **Image Chops Difference**: Detects pixel-level differences.
- **Connected Components Analysis**: Identifies the largest defect cluster in an image.

### Database & Performance Optimization
- **Multithreading**: Improves performance by handling multiple processes simultaneously.
- **MongoDB Indexing**: Enhances query speed for retrieving image metadata.
- **Batch Processing**: Efficiently processes multiple images in parallel.
- **Asynchronous Updates**: Ensures smooth database transactions without blocking execution.


## Primary Functions

1. **Database Connection**
   - `def connect_db()`: Connects to MySQL and returns a connection object.
2. **Insert Inspection Record**
   - `def insert_inspection(connection, rail_id, ...)`: Stores inspection results in the MySQL table.
3. **Find Largest Cluster**
   - `def find_largest_cluster(diff_image, resolution)`: Identifies the largest defect cluster in an image.
4. **Read Rail ID from CSV**
   - `def read_rail_id()`: Reads rail IDs from `railid.csv`.
5. **Parse Rail ID Information**
   - `def parse_rail_id_info(rail_id)`: Extracts shift-grade from rail ID pattern.
6. **Mask Image Processing**
   - `def mask_image(filename1)`: Applies edge detection and thresholding to enhance defects.
7. **Mean Squared Error (MSE) Calculation**
   - `def mse(img1, img2)`: Measures difference between two images.
8. **Image Chops Difference**
   - `def imageChops(filename1, filename2)`: Detects pixel differences using PIL’s ImageChops.
9. **Pixel Match Difference**
   - `def imageDiff(filename1, filename2)`: Uses pixelmatch to compare images.
10. **Update MongoDB Inference**
    - `def update_dd_inference(rail_id, image_paths)`: Updates MongoDB `DD_inference` status.
11. **Process Camera Folder**
    - `def process_camera_folder(connection, rail_id, camera_id, image_paths, distance)`: Compares images and inserts inspection results into MySQL.
12. **Process Rail Data**
    - `def process_rail_data(rail_data, rail_id, db_connection)`: Iterates over all camera data and triggers processing.
13. **Main Processing Job**
    - `def main_job()`: Manages workflow, reads rail data, and processes inspections.


## Troubleshooting and Common Issues

- **Image Loading Failures**: Verify image paths in MongoDB and check file system accessibility.
-   Common error: `FileNotFoundError: [Errno 2] No such file or directory`.

- **Database Connection Errors**: Ensure credentials are correct and databases are running.
-  Possible error message: `mysql.connector.errors.OperationalError: (1045, 'Access denied for user')`.

- **Empty Rail IDs**: Ensure `railid.csv` contains valid 11-character IDs.
-  Error example: `ValueError: Invalid Rail ID length`.

- **Performance Bottlenecks**: Optimize image processing by adjusting resize factors or parallelizing database updates.
- Monitor logs for delays in `Processing time exceeded threshold` warnings.


## Conclusion
This documentation provides a comprehensive overview of the Dimensional Inspection System, ensuring robust quality control in rail manufacturing.

