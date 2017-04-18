# Apache Ignite Microservices Example

The example demonstrates how to realize Microservices based architecture on top of Apache Ignite covered in a series of
blog posts:
* Part I: https://www.gridgain.com/resources/blog/running-microservices-top-memory-data-grid-part-i
* Part II: https://www.gridgain.com/resources/blog/microservices-top-memory-data-grid-part-ii
* Part III: https://www.gridgain.com/resources/blog/microservices-top-memory-data-grid-part-iii

To execute the example do the following:
<lu>
<li>
Start one or more instances of data nodes using DataNodeStartup.
</li>
<li>
Start one or more instances of Maintenance service nodes using MaintenanceServiceNodeStartup.
</li>
<li>
Start one or more instances of Vehicle service nodes using VehicleServiceNodeStartup.
</li>
<li>
Execute an internal test application (connects to the microservices using Ignite API) using TestAppStart.
</li>
<li>
Execute an external test application (connects to the microservices using direct sockets API) using ExternalTestApp.
</li>
</lu>
