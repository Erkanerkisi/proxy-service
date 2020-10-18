## **Proxy Service**

This is springboot application that can be used to call https rest and soap web services from plsql.
Sometime we can not reach out https services from oracle db due to restrictions. This service is like proxy services to get https ws from http.

### **Docker Setup**

docker build --tag proxy-services . <br/>
docker run --rm -i -t -p 8080:8080 proxy-server



#### Example Rest Call Request For Restful Ws

**uri** : Url for restful ws <br/>
**httpMethod**: Http Method like GET, POST, PUT etc.<br/>
**requestBody**: Raw request body if exist
**headers**: header parameters if exists
**parameters**: If you call get restful service with uri variables use this map to replace parameters (example: /{id} -> use {"id" : 1} etc.)


    "uri": "http://dummy.restapiexample.com/api/v1/create",
    "httpMethod": "POST",
    "requestBody": 	"{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}",
    "headers": {"Content-Type" :  "application/json"},
    "parameters": null


#### Example Rest Call Request For Soap Ws

**uri** : Url for restful ws <br/>
**requestBody**: Raw request body if exist <br/>
**headers**: header parameters if exists <br/>


     "uri": "http://www.learnwebservices.com/services/hello",
     "requestBody": "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hel=\"http://learnwebservices.com/services/hello\"><soapenv:Header/><soapenv:Body><hel:SayHello><hel:HelloRequest><hel:Name>erkan</hel:Name></hel:HelloRequest></hel:SayHello></soapenv:Body></soapenv:Envelope>",
     "headers": {
         "Content-Type": "application/xml"
     }
 