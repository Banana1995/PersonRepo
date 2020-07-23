# JDB debug

- 使用如下命令启动进程，开启jvm的debug模式，端口可自定义，默认为5005

  > `-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005`

- 使用如下命令，将jdb debugger附到已经运行的jvm上

  >  `jdb -attach 5005`

