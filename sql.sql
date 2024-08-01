CREATE DATABASE cosmetic;

USE cosmetic;

-- Bảng Categories
CREATE TABLE Categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Bảng Subcategories
CREATE TABLE Subcategories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES Categories(id)
);

-- Bảng Products
CREATE TABLE Products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    subcategory_id INT,
    brand VARCHAR(255),
    FOREIGN KEY (subcategory_id) REFERENCES Subcategories(id)
);

-- Bảng ProductVariants
CREATE TABLE Product_Variants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    color VARCHAR(255),
    stock_quantity INT,
    FOREIGN KEY (product_id) REFERENCES Products(id)
);

-- Bảng Customers (Khách hàng)
CREATE TABLE Accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(255),
    address TEXT,
    role INT
);

-- Bảng Orders
CREATE TABLE Orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2),
    status VARCHAR(255),
    FOREIGN KEY (account_id) REFERENCES Accounts(id)
);

-- Bảng OrderDetails
CREATE TABLE Order_Details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_variant_id INT,
    quantity INT,
    price DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES Orders(id),
    FOREIGN KEY (product_variant_id) REFERENCES Product_Variants(id)
);

-- Bảng Cart
CREATE TABLE Cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES Accounts(id)
);

-- Bảng CartItems
CREATE TABLE Cart_Items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id INT,
    product_variant_id INT,
    quantity INT,
    FOREIGN KEY (cart_id) REFERENCES Cart(id),
    FOREIGN KEY (product_variant_id) REFERENCES Product_Variants(id)
);

-- Bảng Product_Variant_Images để lưu thông tin hình ảnh của biến thể sản phẩm
CREATE TABLE Product_Variant_Images (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_variant_id INT,
    image_url VARCHAR(255),
    FOREIGN KEY (product_variant_id) REFERENCES Product_Variants(id)
);

-- Bảng Videos để lưu thông tin video của sản phẩm
CREATE TABLE Videos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    video_url VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES Products(id)
);
