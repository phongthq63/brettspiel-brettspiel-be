<h3>Opentelemetry<h3>

**Run**:
>java -javaagent:path/to/opentelemetry-javaagent.jar <br>
      &emsp;&emsp; -Dotel.javaagent.extensions=path/to/agent-extension-0.0.1-SNAPSHOT.jar <br>
      &emsp;&emsp; -jar myapp.jar

**Config**:
 - https://github.com/open-telemetry/opentelemetry-java/blob/main/sdk-extensions/autoconfigure/README.md#propagator