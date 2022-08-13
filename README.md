## A scoffold application for SpringBoot and Hasura

It's very productive to use SpringBoot and Hasura together. Frontend developers could even start the work before writing the backend.

Usually, it’s inappropriate to communicate the browser direct with the Hasura server. So we could add a proxy at SpringBoot using the SpringCloud gateway, by adding the filters to the proxy we could archive most fine control to the data flow. 


![architecture](/images/bigpicture.png)
