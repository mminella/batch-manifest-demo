# Example repo with a manifest

To run this example on CloudFoundry
* Update the `manifest.yml` with the correct path for your built jar file.
* Confirm that your space has a MySql instance that's name matches what is in the `manifest.yml` file
* Build the application via `./mvnw clean install`
* After logging into CloudFoundry with the `cf` CLI, from the root of the project, execute the command `cf push`
* To launch the task, execute the following command:
```
cf run-task spring-cloud-task-example 'JAVA_OPTS="-agentpath:$PWD/.java-buildpack/open_jdk_jre/bin/jvmkill-1.16.0_RELEASE=printHeapHistogram=1 -Djava.io.tmpdir=$TMPDIR -Djava.ext.dirs=$PWD/.java-buildpack/container_security_provider:$PWD/.java-buildpack/open_jdk_jre/lib/ext -Djava.security.properties=$PWD/.java-buildpack/java_security/java.security $JAVA_OPTS" && CALCULATED_MEMORY=$($PWD/.java-buildpack/open_jdk_jre/bin/java-buildpack-memory-calculator-3.13.0_RELEASE -totMemory=$MEMORY_LIMIT -loadedClasses=12325 -poolType=metaspace -stackThreads=250 -vmOptions="$JAVA_OPTS") && echo JVM Memory Configuration: $CALCULATED_MEMORY && JAVA_OPTS="$JAVA_OPTS $CALCULATED_MEMORY" && MALLOC_ARENA_MAX=2 SERVER_PORT=$PORT eval exec $PWD/.java-buildpack/open_jdk_jre/bin/java $JAVA_OPTS -cp $PWD/. org.springframework.boot.loader.JarLauncher'
```

For convenience, you can also run as follows:

```
cf run-task spring-cloud-task-example "$(cf curl /v3/apps/$(cf app spring-cloud-task-example --guid)/droplets/current | jq -r .process_types.task)"
```

* To view the results, run the command `cf logs --recent spring-cloud-task-example`
