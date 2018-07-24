Repository的使用

## Repository的概念
在Spring中有Repository的概念，repository原意指的是仓库，即数据仓库的意思。Repository居于业务层和数据层之间，将两者隔离开来，在它的内部封装了数据查询和存储的逻辑。这样设计的好处有两个：

降低层级之间的耦合：更换、升级ORM引擎（Hibernate）并不会影响业务逻辑
提高测试效率：如果在测试时能用Mock数据对象代替实际的数据库操作，运行速度会快很多

## Repository和DAO的区别
DAO是传统MVC中Model的关键角色，全称是Data Access Object。DAO直接负责数据库的存取工作，乍一看两者非常类似，但从架构设计上讲两者有着本质的区别：

Repository蕴含着真正的OO概念，即一个数据仓库角色，负责所有对象的持久化管理。DAO则没有摆脱数据的影子，仍然停留在数据操作的层面上。Repository是相对对象而言，DAO则是相对数据库而言，虽然可能是同一个东西 ，但侧重点完全不同。

## 三种Repository介绍
在Spring和Spring Data JPA中，有三种Repository接口方便开发者直接操作数据仓库。
它们之间的关系如下：

![](https://i.imgur.com/kReYn9F.png)

### CrudRepository

      <S extends T> S save(S entity);
      <S extends T> Iterable<S> save(Iterable<S> entities);
      T findOne(ID id);
      boolean exists(ID id);
      Iterable<T> findAll();
      Iterable<T> findAll(Iterable<ID> ids);
      long count();
      void delete(ID id);
      void delete(T entity);
      void delete(Iterable<? extends T> entities);
      void deleteAll();
CrudRepository类如其名，可以胜任最基本的CRUD操作。其中save方法在可两用，参数中不存在主键时执行insert操作，存在主键则执行update操作，相当于是一个upsert操作。

### PagingAndSortingRepository
    
    Iterable<T> findAll(Sort sort);
      Page<T> findAll(Pageable pageable);

PagingAndSortingRepository继承了CrudRepository接口，增加了分页和排序的方法。

### 分页

要达到分页的目的，需要传入一个Pageble接口对象，controller中代码如下：

     @RequestMapping("/boy/pagelist")
    public ResponseEntity<?> getList(
            @RequestParam(value="page", defaultValue="0") int page,
            @RequestParam(value="size", defaultValue="10") int size ) {

        Pageable pageable = new PageRequest(page, size);
        return new ResponseEntity<Object>(boyService.getBoyPageList(pageable), HttpStatus.OK);
    }

@RequestParam注解，表明了需要传入URL参数page和size，用于计算分页的offset值。
PageRequest是Pageable的实现类，传入初始化page和size即可生成分页参数。

在Service中，仅需加入pageable对象即可达到分页的效果。代码如下：
    
    @Override
    public Iterable<Boy> getBoyPageList(Pageable pageable){
	 return boyDao.findAll(pageable);
	}

访问地址：http://localhost:8080/boy/pagelist?page=0&size=5

得到以下结果

    {
      "content": [
    {
      "id": 1,
      "name": "zjx",
      "age": 28
    },
    {
      "id": 2,
      "name": "zjxsss",
      "age": 29
    },
    {
      "id": 3,
      "name": "wang",
      "age": 1111
    },
    {
      "id": 4,
      "name": "wang",
      "age": 1111
    },
    {
      "id": 5,
      "name": "wang",
      "age": 222
    }
      ],
      "last": false,
      "totalPages": 4,
      "totalElements": 19,
      "size": 5,
      "number": 0,
      "sort": null,
      "first": true,
      "numberOfElements": 5
    }

在content中有查询的数组信息，还包括totalPages等分页信息。

### 排序

与分页类似，要达到排序的目录，仅需要传入Sort对象即可，controller中代码如下：

    @GetMapping("/boy/sortlist")
    public ResponseEntity<?> getSortList() {
  
    Sort sort = new Sort(Sort.Direction.DESC, "age");
    
    return new ResponseEntity<Object>(boyService.getBoySortList(sort), HttpStatus.OK);
    }

在Service中需要新增

	@Override
    public Iterable<Boy> getBoySortList(Sort sort) {
        return boyDao.findAll(sort);
    }

访问地址：http://localhost:8080/boy/sortlist

 可以看到根据“age” 排序了

### 排序后分页
    
     Sort sort = new Sort(Sort.Direction.DESC, "name");
      Pageable pageable = new PageRequest(page, size, sort);

## JpaRepository新增功能

1查询列表（返回值为List）
2批量删除
3强制同步
4Example查询