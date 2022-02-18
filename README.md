# Adapter-OSM-File-for-Neo4j
- OSM is Open Street Map (https://www.openstreetmap.org/)
- You can download geographic data from this website with file .OSM
- This program parse this file to java objects
- Then it divides map to small rectangle pieces
- These pieces (grounds) and obstacles on them will be made to java object
- Then those will be uploaded into neo4j graph-databse (https://neo4j.com/product/neo4j-graph-database/)
- This project is for drone to calculate best route. And this is a base project (data adapter and generating data) for that.