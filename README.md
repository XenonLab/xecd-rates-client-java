<p align="">
    <a href="http://www.xe.com" target="_blank">
        <img src="https://avatars.githubusercontent.com/u/8235441?s=96&v=4"/>
    </a>
</p>

# XE Currency Data Client - Java

XE.com Inc. is the World's Trusted Currency Authority. This project provides an SDK to interface with our XE Currency Data (XECD) product.

XE Currency Data is a REST API that gives you access to live and historic mid-market exchange rates between all of our supported currencies. 

You will need an api key and secret to use this SDK. Sign up for a [free trial][5] or register for a [full account][6].

## Installation

The preferred way to install this package is from [Maven Central][4].

To add a dependency on XECD Rates Client using Maven, use the following:

```xml
<dependency>
  <groupId>com.xe.xecd</groupId>
  <artifactId>xecd-rates-client</artifactId>
  <version>0.1</version>
</dependency>
```

To add a dependency using Gradle:

```groovy
dependencies {
  compile 'com.xe.xecd:xecd-rates-client:0.1.+'
}
```

This package follows [semantic versioning][3].

## Usage

```java
package com.example;

import com.xe.xecdApiClient.model.HistoricRatesResponse;
import com.xe.xecdApiClient.config.XecdApiConfigBean;
import com.xe.xecdApiClient.service.XecdApiService;
import com.xe.xecdApiClient.service.XecdApiServiceFactory;

public class Main
{
  private XecdApiService apiService;

  public void main() throws XecdApiException
  {
    XecdApiConfigBean config = new XecdApiConfigBean();
    config.setAccountId("<YOUR_ACCOUNT_ID>");
    config.setApiKey("<YOUR_API_KEY>");
    apiService = XecdApiServiceFactory.createXecdAPIService(config);

    try {
      HistoricRateResponse historicRateResponse = apiService.historicRate("CAD", "USD,GBP", "2017-09-14", null, 1.00, false, false);
    }
    catch(XecdApiException e)
    {
      System.out.println(e.getMessage());
    }
  }
}
```

## Documentation

[Technical Specifications][2]

## Contributing

xecd-rates-client-java is an open-source project. Submit a pull request to contribute!

## Testing

```bash
cd xecd-rates-client-java

# Unit tests.
./gradlew test

# Integration tests.
export XECD_ACCOUNT_ID=<YOUR_ACCOUNT_ID>
export XECD_API_KEY=<YOUR_API_KEY>
./gradlew test
```

## Security Issues

If you discover a security vulnerability within this package, please **DO NOT** publish it publicly. Instead, contact us at **security [at] xe.com**. We will follow up with you as soon as possible.

## About Us

[XE.com Inc.][1] is The World's Trusted Currency Authority. Development of this project is led by the XE.com Inc. Development Team and supported by the open-source community.

[1]: http://www.xe.com
[2]: http://www.xe.com/xecurrencydata/XE_Currency_Data_API_Specifications.pdf
[3]: http://semver.org/
[4]: http://search.maven.org/
[5]: https://xecd.xe.com/account/signup.php?freetrial
[6]: http://www.xe.com/xecurrencydata/
