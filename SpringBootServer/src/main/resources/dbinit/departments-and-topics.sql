INSERT INTO department (name) VALUES ('FX'),('Credit'),('Equities'),('Retail')
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO topic (name) VALUES ('Remote Connectivity'),('Messaging'),('Servers'),('Databases')
ON DUPLICATE KEY UPDATE name=name;