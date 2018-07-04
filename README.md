# CoinTossX
CoinTossX is a low latency high throughput matching engine that follows the rules of the Johannesburg Stock Exchange (JSE). The system allows users to connect using a client library. Users send orders to the exchange and receive updates of the status of their orders and market data updates of all the stocks on the exchange. A website allows users to view the stocks on the exchange in real time. The system was developed using Java and its related technologies. CoinTossX was developed as part of a master's dissertation from Witwatersrand University. 

# Installation

Download Java. The Java version should be version 8+. Check the Java version on the server
```sh
$ java -version 
java version "1.8.0_60"
```
Clone the repository
```sh
git clone https://github.com/mohellen/CoinTossX.git
```
Build the project (excluding tests)
```sh
gradle build -x test
```
Deploy to the University server
```sh
gradle -PenvProp=local.properties -PsoftwarePath=/tmp clean installDist bootRepackage copyResourcesToInstallDir copyToDeploy deleteDeployZip deployZip deployToWitsServer 
```
Deploy to the CHPC server
```sh
gradle -PenvProp=chpcServer.properties clean installDist bootRepackage copyResourcesToInstallDir copyToDeploy deleteDeployZip deployZip deployToCHPCServer
```
Start the Gateways on the server using the start scripts
```sh
./startAll.sh
```
# Example Code
The client login details must be defined in the clientData.csv file. 

```java
//These values should be stored and retrevied from the clientData.csv file
String url = udp://localhost:5000
int streamId = 10
int compId = 1 
String password = test111111

//Connect to the Trading Gateway
GatewayClientImpl tradingGatewayPub = new GatewayClientImpl(); 
tradingGatewayPub.connectInput(url,streamId);

//Login to the Trading Gateway
LogonBuilder logonBuilder = new LogonBuilder();         
DirectBuffer buffer = logonBuilder.compID(compId)
.password(password.getBytes())  
.newPassword(password.getBytes())
.build();

tradingGatewayPub.send(buffer);
```

Send a new order to the Trading Gateway
```java
public DirectBuffer createNewOrder(long volume, 
                                   long price,
                                   SideEnum side,
                                   OrdTypeEnum orderType){

NewOrderBuilder newOrderBuilder = new NewOrderBuilder();

DirectBuffer directBuffer = newOrderBuilder.compID(clientData.getCompID())
            .clientOrderId("1234".getBytes())
            .account("account123".getBytes())
            .capacity(CapacityEnum.Agency)
            .cancelOnDisconnect(CancelOnDisconnectEnum.DoNotCancel)
            .orderBook(OrderBookEnum.Regular)
            .securityId(securityId)
            .traderMnemonic("John".getBytes())
            .orderType(orderType)
            .timeInForce(TimeInForceEnum.Day)
            .expireTime("20150813-23:00:00".getBytes())
            .side(side)
            .orderQuantity((int) volume)
            .displayQuantity((int) volume)
            .minQuantity(0)
            .limitPrice(price)
            .stopPrice(0)
            .build();
    return directBuffer; 
}

//Send new order to Trading Gateway
tradingGatewayPub.send(createNewOrder(1200, 25034, SideEnum.Buy, OrdTypeEnum.Limit));
```

# Screenshots
Splash Screen
![](https://user-images.githubusercontent.com/10845401/28061338-9b8ec63e-666d-11e7-9e3c-3e6fc84b6cc1.png)
![](https://user-images.githubusercontent.com/10845401/28061427-ddc9687e-666d-11e7-8b2f-83ecfe06514e.png)
![](https://user-images.githubusercontent.com/10845401/28061443-ec5a5cae-666d-11e7-8bfb-5417d7fb128f.png)

Simulation
![](https://user-images.githubusercontent.com/10845401/28061471-0abc67c8-666e-11e7-8f74-5f8bc937a817.png)

Limit Order Book
![](https://user-images.githubusercontent.com/10845401/28061480-0fa64858-666e-11e7-80c6-88cb93d1b975.png)

