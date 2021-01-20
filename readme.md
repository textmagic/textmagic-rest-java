## Synopsis

The TextMagic API Java wrapper can save you a lot of time, as it includes all the necessary API commands and tests. It only takes a few seconds to download it from GitHub and to install it into your own app or software. After installation, you’ll then be able to send text messages.

<!---
sms api for JAVA
JAVA api to send sms
JAVA sms api
send sms from JAVA
JAVA send sms messages
JAVA library send sms messages
-->

## Code Example
```
package com.textmagic.sdk;
  
import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.instance.*;
import java.util.Arrays;
  
public class Example{
  public static void main(String[] args) throws RestException {
    RestClient client = new RestClient("<USERNAME>", "<APIV2_TOKEN>");

    TMNewMessage m = client.getResource(TMNewMessage.class);
    m.setText("Hello from TextMagic Java");
    m.setPhones(Arrays.asList(new String[] {"99900000"}));
    try {
      m.send();
    } catch (final RestException e) {
      System.out.println(e.getErrors());
      throw new RuntimeException(e);
    }
    System.out.println(m.getId());
  }
} 
```

## Installation Instructions
### Quick Installation

The easiest way to install the TextMagic Java wrapper is from Maven. You can add the following dependency to your existing project, specifying the latest version in the version tag:
```
<dependency>
    <groupId>com.textmagic.sdk</groupId>
    <artifactId>textmagic-java-sdk</artifactId>
    <version>1.2.0</version>
</dependency>
```
### Manual Installation

You can also install the TextMagic Java wrapper from the GitHub repository using git. Run the following commands:
```
git clone git://github.com/textmagic/textmagic-rest-java.git
cd textmagic-rest-java
mvn install
```

## Requirements
The Java wrapper has the following requirements:
* Java SE6 or higher
* Apache Maven 2.0 or higher


## API Reference
* https://www.textmagic.com/docs/api/java/
* https://rest.textmagic.com/api/v2/doc


## License

The library is available as open source under the terms of the [MIT License](http://opensource.org/licenses/MIT).
