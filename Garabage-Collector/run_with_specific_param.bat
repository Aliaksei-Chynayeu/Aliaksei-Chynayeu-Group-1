java ... -XX:+UseConcMarkSweepGC -Xms=6m -Xmx=18m -XX:NewSize=2m -XX:MaxNewSize=2m -XX:PermSize=20m -XX:MaxPermSize=30m
rem -XX:+UseConcMarkSweepGC               point out to use CMS collector
rem -Xms=6m -Xmx=18m                      set up min/initial and max size of heap
rem -XX:NewSize=2m -XX:MaxNewSize=2m      set up the exact size of new generation
rem -XX:PermSize=20m -XX:MaxPermSize=30m  set up min/initial and max size of permanent generation
