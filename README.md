![ServerStatistics](/assets/Header.png)
ServerStatistics is a powerful Minecraft server plugin that generates advanced statistics about your server or network.
Unlike any other statistics plugin, this plugin focuses on the server/network itself rather than the players on it.
Where this plugin shines however is the fact that everything is sent to an external panel, rather than creating a messy
in-game setup. Another key feature of this plugin is that it hooks into other plugins as much as possible and generates
statistics about those plugins too. If you wish to create statistics about your server using a simple plugin, you're at
the right place. You can for example create graphs on how many times the Jesus hack was blocked by any anti cheat! How
cool is that?!

# API usage
This plugin offers an extremely easy to use API, that takes seconds to set up. First, you must add the dependency to
your repository.

## Precaution
Currently, Github packages require you to authenticate even on public, open source packages. While we wait for Github
to address this, as it makes no sense, the easiest installation approach would be to clone the repository and install
the package locally.
```shell script
git clone https://github.com/MrDienns/ServerStatistics.git
cd ServerStatistics
mvn install
```

## Maven
To install the API in your project using Maven;
```xml
<dependency>
    <groupId>io.serverstatistics</groupId>
    <artifactId>api</artifactId>
    <version>2.0.0</version>
</dependency>
```

## Gradle
To install the API in your project using Gradle;
```groovy
compile group: 'io.serverstatistics', name: 'api', version: '2.0.0'
```

# Usage
The API is extremely straight forward. There's two simple annotations that are available. There is the ServerStatistic
annotation, which is used to mark public static methods as periodic statistical endpoints. The function may return any
object, from primitives, to objects and collections. The plugin will take care of everything else. The method will
periodically be called, and the configured mapping will be applied on the returned metrics (coming soon). 

Then there is also the ServerTag annotation which is a global tag annotation. You could use this to expose specific
tags, such as server type, data center region or specific settings you want to use as tags, such as maximum player
count. You can also simply configure tags, but if you want to specify them through code; you can.

## Registering metrics
All classes which have a ServerStatistic or ServerTag annotation must first be registered.
```java
@Override
public void onEnable() {
    StatisticRegistry.get().addStatisticProvider(new PlayerStatistic());
}
```

## Exposing metrics
For example, reporting all online players;
```java
import io.serverstatistics.api.StatisticProvider;

public class PlayerStatistic implements StatisticProvider {

    /**
     * A simple method public the online players.
     * Any configured mapping applies to the returned object on the collection.
     */
    @ServerStatistic(name = "Online players")
    public List<Player> getPlayerCount() {
       return Bukkit.getOnlinePlayers();
    }
}
```

Reporting current memory metrics;
```java
public class RuntimeStatistics implements StatisticProvider {

    /**
     * Returns the total allocated memory in bytes.
     */
    @ServerStatistic(name = "Total memory")
    public long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }
    
    /**
     * Returns the total free memory in bytes.
     */
    @ServerStatistic(name = "Free memory")
    public long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }
}
```

## Exposing tags
In addition to global tags being configurable, you can also specify them in code. For example, a simple tag which
exposes the server type;
```java
public class ServerTypeTags implements StatisticProvider {

    /**
     * A simple method which returns the server type.
     */
    @ServerTag(name = "Server type")
    public String getServerType() {
        return "Survival";
    }
}
```

# Example projects
Below are a few very small example projects in this repository which showcase the usage of this API.
* [UniqueJoinCounter](example/JoinCounter) - Shows a primitive statistic and global tag