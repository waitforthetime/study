# rabbitmq安装

# 环境
* 系统：mac，已经安装了homebrew

# 安装
* 安装，前提是安装了homebrew
```
brew install rabbitmq
```
* <font color=red>Tips:</font>
```
brew 安装的软件包都在/usr/local/Cellar目录下
```
* 配置环境变量
```
vim .bash_profile

RABBIT_HOME=/usr/local/Cellar/rabbitmq/3.7.8
PATH=$PATH:$RABBTI_HOME/sbin
export PATH
然后保存退出
source .bash_profile
```
# 验证
* 启动rabbitmq
```
通过brew安装的，brew已经提供了后台启动方式:
brew services start rabbtimq
也可以输入
rabbitmq-server -detached
以守护线程的方式启动
或者直接输入
rabbitmq-server
启动rabbitmq
后者启动时，窗口不能关闭也不能执行其他操作
```
* 启动rabbitmq界面管理插件(默认是已经启用了这个插件了的)
```
rabbitmq-plugins enbale rabbitmq_management
```
* 使用管理界面
```
打开浏览器，输入：localhost:15672，然后回车
进入登陆界面，默认账户密码：guest
```
* 创建自己的管理账户
```
rabbitmqctl add_user wangda wangdapass
创建了名为wangda，密码为wangdapass的账户
```
* 标记身份
```
rabbitmqctl set_user_tags wangda administrator
为wangda赋予了administrator身份
```
* 分配权限
```
rabbitmqctl set_permissions -p "/" wangda ".*" ".*" ".*"
为wangda分配了/目录下的配置、读、写权限
```

# 番外篇
* 对rabbitmq角色和权限的管理，开发人员简单了解下即可

## 用户角色
* none
    * 不能访问management plugin
* management
    * 可以通过AMQP做的任何事
    * 列出自己可以通过AMQP登入的virtual hosts
    * 查看自己的virtual hosts、queues、exchange和bindings
    * 查看和关闭自己的channels、connections
    * 查看有关自己的virtual hosts的"全局"的统计信息，包含其他用户在这些virtual hosts上的活动
* policymaker
    * management可以做的任何事
    * 查看、创建、删除自己的virtual hosts所属的policies和parameters
* monitoring
    * management可以做的任何事
    * 列出所有的virtual hosts，包括他们不能登陆的virtual hosts
    * 查看其他用户channels和connections
    * 查看节点级别的数据如clustering和memory情况
    * 查看真正的关于所有virtual hosts的全局的统计信息
* administrator
    * policymaker和monitoring可以做的任何事
    * 创建、删除virtual hosts
    * 查看、创建、删除users
    * 查看、创建、删除permissions
    * 关闭其他用户connections

## 创建用户和设置角色
```
# 创建管理员
rabbitmqctl add_user  user_admin  passwd_admin
rabbitmqctl set_user_tags user_admin administrator
#创建监控用户
rabbitmqctl add_user  user_monitoring  passwd_monitor
rabbitmqctl set_user_tags user_monitoring monitoring
#创建项目专用用户
rabbitmqctl  add_user  user_proj  passwd_proj
rabbitmqctl set_user_tags user_proj management
#查看用户列表
rabbitmqctl list_users
```