<template>
  <v-app>

    <!--    文章目录-->
    <v-card class="float-component mr-10" style="max-width: 30vw">
      <v-icon :icon="toggle_icon" class="ml-10" @click="handle_toggle_titles"></v-icon>
      <v-treeview
        v-show="toggle_titles"
        :items="titles"
        activatable
        color="primary"
        open-all
        open-on-click
      >
        <template v-slot:prepend="{ item }">
          <v-icon :icon="icons[item.level]"></v-icon>
        </template>
      </v-treeview>
    </v-card>


    <v-container class="position:relative my-10" max-width="1000" style="z-index: 1">
      <!--      文章内容-->
      <v-card>
        <v-row>
          <v-col>
            <v-parallax src="https://cdn.vuetifyjs.com/images/parallax/material2.jpg" style="height: 500px">
            </v-parallax>
          </v-col>
        </v-row>
        <v-row class="ml-10">
          <v-col v-for="item in category" cols="1">
            <v-chip class="my-2" color="primary">{{ item }}</v-chip>
          </v-col>
        </v-row>
        <v-row class="mx-2 ml-10">
          <div class="text-h3 font-weight-bold">{{ article.title }}</div>
        </v-row>
        <v-row class="my-10">
          <v-col class="ml-10">
            <v-icon>mdi-eye</v-icon>
            {{ article.watchNum }}
          </v-col>
          <v-col class="d-flex justify-end align-center mr-10">
            <v-icon size="small">mdi-calendar-multiple</v-icon>
            {{ article.create_time }}
          </v-col>
        </v-row>
        <v-divider class="my-10"></v-divider>
        <v-row>
          <v-col>
            <v-md-preview :text="content"></v-md-preview>
          </v-col>
        </v-row>
      </v-card>
    </v-container>
  </v-app>
</template>

<script lang="ts" setup>


import type IArticleShow from "@/types/IArticleShow";

const toggle_titles = ref(true)
const toggle_icon = computed(() => {
  return toggle_titles.value ? 'mdi-chevron-right' : 'mdi-chevron-left'
})
const handle_toggle_titles = () => {
  toggle_titles.value = !toggle_titles.value
  console.log(toggle_titles.value)
}

// 文章目录的 icon
const icons = [
  'mdi-account-box-outline',
  'mdi-account-circle-outline',
  'mdi-account-outline',
  'mdi-account-search-outline',
  'mdi-account-star-outline',
  'mdi-account-supervisor-outline',
]

const content = ref("\n" +
  "# 一 JPA 基础：常见操作\n" +
  "\n" +
  "## 1.相关依赖\n" +
  "\n" +
  "我们需要下面这些依赖支持我们完成这部分内容的学习：\n" +
  "\n" +
  "```\n" +
  " <dependencies>\n" +
  "        <dependency>\n" +
  "            <groupId>org.springframework.boot</groupId>\n" +
  "            <artifactId>spring-boot-starter-web</artifactId>\n" +
  "        </dependency>\n" +
  "        <dependency>\n" +
  "            <groupId>org.springframework.boot</groupId>\n" +
  "            <artifactId>spring-boot-starter-data-jpa</artifactId>\n" +
  "        </dependency>\n" +
  "        <dependency>\n" +
  "            <groupId>mysql</groupId>\n" +
  "            <artifactId>mysql-connector-java</artifactId>\n" +
  "            <scope>runtime</scope>\n" +
  "        </dependency>\n" +
  "        <dependency>\n" +
  "            <groupId>org.projectlombok</groupId>\n" +
  "            <artifactId>lombok</artifactId>\n" +
  "            <optional>true</optional>\n" +
  "        </dependency>\n" +
  "        <dependency>\n" +
  "            <groupId>org.springframework.boot</groupId>\n" +
  "            <artifactId>spring-boot-starter-test</artifactId>\n" +
  "            <scope>test</scope>\n" +
  "        </dependency>\n" +
  "    </dependencies>\n" +
  "```\n" +
  "\n" +
  "### 2.配置数据库连接信息和JPA配置\n" +
  "\n" +
  "下面的配置中需要单独说一下 `spring.jpa.hibernate.ddl-auto=create`这个配置选项。\n" +
  "\n" +
  "这个属性常用的选项有四种：\n" +
  "\n" +
  "1. `create`:每次重新启动项目都会重新创新表结构，会导致数据丢失\n" +
  "2. `create-drop`:每次启动项目创建表结构，关闭项目删除表结构\n" +
  "3. `update`:每次启动项目会更新表结构\n" +
  "4. `validate`:验证表结构，不对数据库进行任何更改\n" +
  "\n" +
  "但是，**一定要不要在生产环境使用 ddl 自动生成表结构，一般推荐手写 SQL 语句配合 Flyway 来做这些事情。**\n" +
  "\n" +
  "```java\n" +
  "spring.datasource.url=jdbc:mysql://localhost:3306/springboot_jpa?useSSL=false&serverTimezone=CTT\n" +
  "spring.datasource.username=root\n" +
  "spring.datasource.password=123456\n" +
  "# 打印出 sql 语句\n" +
  "spring.jpa.show-sql=true\n" +
  "spring.jpa.hibernate.ddl-auto=create\n" +
  "spring.jpa.open-in-view=false\n" +
  "# 创建的表的 ENGINE 为 InnoDB\n" +
  "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL55Dialect\n" +
  "```\n" +
  "\n" +
  "## 3.实体类\n" +
  "\n" +
  "我们为这个类添加了 `@Entity` 注解代表它是数据库持久化类，还配置了主键 id。\n" +
  "\n" +
  "```java\n" +
  "import lombok.Data;\n" +
  "import lombok.NoArgsConstructor;import javax.persistence.Column;\n" +
  "import javax.persistence.Entity;\n" +
  "import javax.persistence.GeneratedValue;\n" +
  "import javax.persistence.GenerationType;\n" +
  "import javax.persistence.Id;\n" +
  "@Entity\n" +
  "@Data\n" +
  "@NoArgsConstructor\n" +
  "public class Person {        \n" +
  "\t@Id\n" +
  "    @GeneratedValue(strategy = GenerationType.IDENTITY)\n" +
  "    private Long id;\n" +
  "    @Column(unique = true)\n" +
  "    private String name;\n" +
  "    private Integer age;    \n" +
  "\tpublic Person(String name, Integer age) {\n" +
  "        this.name = name;\n" +
  "        this.age = age;\n" +
  "    }}\n" +
  "```\n")


const titles = computed(() => {
  const reg = /^(#+)\s+(.+)$/gm;
  // 定义一个虚拟根节点，便于统一管理
  const root = {level: 0, title: 'root', children: [] as any[]};
  const stack = [root];

  Array.from(content.value.matchAll(reg)).forEach(match => {
    const curr_title = match[2].trim();
    const curr_level = match[1].length;
    const node = {title: curr_title, level: curr_level, children: [] as any[]};

    // 弹出栈中层级大于或等于当前标题层级的节点，找到正确的父节点
    while (stack.length && stack[stack.length - 1].level >= curr_level) {
      stack.pop();
    }

    // 将当前节点作为子节点添加到当前栈顶节点的 children 中
    stack[stack.length - 1].children.push(node);
    // 将当前节点入栈
    stack.push(node);
  });

  // 返回根节点的子节点作为标题树（忽略虚拟根）
  return root.children;
});
//
// console.log(titles.value)

const article = reactive<IArticleShow>({
  title: 'Garmins Instinct Crossover is a rugged hybrid smartwatch',
  category: 'pwn, HOF, canary',
  create_time: '2024-01-01',
  author: 'hello',
  watchNum: 10,
})

const category = article.category.split(',')
</script>


<style lang="scss" scoped>
.float-component {
  position: fixed;
  top: 30vh;
  left: 0; /* 固定在左侧 */
  overflow-y: auto;
  z-index: 1000;
  background: white;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
}

</style>
