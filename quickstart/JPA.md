## 类级别注解



- @Entity映射实体类
@Entity（name="tableName"）

name:可选，对应数据库中的一个表。若表名与实体类名相同，则可以省略
注意：使用@Entity时必须指定实体类的主键属性



- @Table

@Table(name="",catalog="",schema="")
@Entity配合使用，只能标注在实体的class定义处，表示实体对应的数据库表的信息。
name:可选，映射表的名称，默认表名和实体名称一致，只有在不一致的情况下才需要指定表名。
catalog 可选，表示Catalog名称，默认为Catalog("")
schema - 可选，表示Schema名称，默认为Schema("").
各种数据库系统对Catalog和Schema的支持和实现方式千差万别


- Embeddable

@Embeddable表示一个非Entity类可以嵌入到另一个Entity类中作为属性而存在。例如：employee类中有个department属性，而departtent是个类

    @Entity 
	public class Employee{ private Department dp; }
	 @Embeddable 
	public class Department{ }

##属性级别注解
- @Id

> 必须，定义了映射到数据库表的主键的属性，一个实体类可以有一个或者多个属性被映射为主键，
可置于主键属性或者getXxx()前，注意：**如果有多个属性定义为主键属性，该实体类必须实现serializable接口
**



- @SequenceGenerator
-  @GeneratedValue
> @GeneratedValue(strategy=GenerationType,generator=""):可选，用于定义主键生成策略
> 
> strategy表示主键生成策略，取值有：
> 
> GenerationType.AUTO：根据底层数据库自动选择（默认）
> 
> GenerationType.INDENTITY：根据数据库的Identity字段生成
> 
> GenerationType.SEQUENCE：使用Sequence来决定主键的取值
> 
> GenerationType.TABLE：使用指定表来决定主键取值，结合@TableGenerator使用

 
- @Column
> @Column-可将属性映射到列，使用该注解来覆盖默认值，@Column描述了数据库表中该字段的详细定义，这对于根据JPA注解生成数据库表结构的工具非常有作用。
> 
> 常用属性：
> 
> name:可选，表示数据库表中该字段的名称，默认情形属性名称一致
> 
> nullable: 可选，表示该字段是否允许为null,默认为true
> 
> unique: 可选，表示该字段是否为唯一标识，默认为false
> 
> length: 可选，表示该字段的大小，仅对String类型的字段有效，默认值225，主键不能使用默认值
> 
> insertable: 可选，表示在ORM框架执行插入操作时，该字段是否应出现INSERTRT语句中，默认为true updateable: 可选，表示在ORM框架执行更新操作时，该字段是否应该出现在UPDATE语句中，默认为true。对于已经创建
> 就不可以更改的字段，该属性非常有用



- @Enbedded

>  @Embedded是注释属性的，表示该属性的类是嵌入类。
>  
> 注意：同时嵌入类也必须标注@Embeddable注解


- @EnbeddedId


> @EmbeddedId使用嵌入式主键类实现复合主键
> 注意：嵌入式主键类必须实现Serializable接口、必须有默认的public无参数的构造方法、必须覆盖equals和hashCode方法




- @Lob

> @Lob注解表示属性将被持久化为Blob或者Clob类型, 具体取决于属性的类型

- @Version
> @Version注解来发现数据库记录的并发操作。当JPA运行时检测到一个并发操作也在试图更改同一条记录。它会抛出一个尝试提交的事务异常。

- @Basic
> - 但对于一些特殊的属性，比如长文本型text、字节流型blob型的数据，在加载Entity时，这些属性对应的数据量比较大，有时创建实体时如果也加载的话，可能严重造成资源的占用。要想解决这些问题，此时就需要设置实体属性的加载方式为惰性加载（LAZY）。

- @javax.persistence.Basic(fetch=FetchType.LAZY,optional=true)
> fetch:抓取策略,延时加载与立即加载

- @transient
    


> 表示该属性并非一个到数据库表的字段映射,ORM 框架将忽略该属性，ORM 默认为@Basic


##关联级别注解
- 一对一单向关外键

- 一对一双向外键

- 多对一（一对多）单向外键：多方持有一方的引用

- 多对一（一对多）双向外键：多方持有一方的引用，一方持有多方的集合

- 多对多单向关联

- 多对多双向关联

  
