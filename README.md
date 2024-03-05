# CitHub-Tools 工具封装代码模版

- 该代码模版以封装覆盖表生成工具SA为例给出。

- 统一格式的Java实现代码位于 src/main/java/com/neo/combinatorial 目录内，其中

  - CTModel类 对应 统一的组合测试模型格式
  - TestSuite类 对应 统一的测试用例集格式

- 工具封装步骤:

  1. 要封装工具的代码实现后使用Maven进行build，build完整后的jar包位于target目录内。

  2. cd 至 src/main/docker 目录内。

  3. 修改目录下的Dockerfile文件，只需要修改`ADD SA-1.0.jar app.jar`中的`SA-1.0.jar`为build出的jar包即可。

  4. docker build，至此工具封装完毕。

     
