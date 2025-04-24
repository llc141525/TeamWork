<template>
  <v-app>
    <!--    文章目录-->
    <v-card
      class="float-component mr-10"
      style="max-width: 30vw"
    >
      <v-icon
        :icon="toggle_icon"
        class="ml-10"
        @click="handle_toggle_titles"
      />
      <v-treeview
        v-show="toggle_titles"
        :items="titles"
        activatable
        color="primary"
        open-all
        open-on-click
      >
        <template #prepend="{ item }">
          <v-icon :icon="icons[item.level]" />
        </template>
      </v-treeview>
    </v-card>


    <v-container
      class="position:relative my-10"
      max-width="1000"
      style="z-index: 1"
    >
      <!--      文章内容-->
      <v-card>
        <v-row>
          <v-col>
            <v-parallax
              src="https://cdn.vuetifyjs.com/images/parallax/material2.jpg"
              style="height: 500px"
            />
          </v-col>
        </v-row>
        <v-row class="ml-10">
          <v-col
            v-for="item in category"
            cols="1"
          >
            <v-chip
              class="my-2"
              color="primary"
            >
              {{ item }}
            </v-chip>
          </v-col>
        </v-row>
        <v-row class="mx-2 ml-10">
          <div class="text-h3 font-weight-bold">
            {{ article.title }}
          </div>
        </v-row>
        <v-row class="my-10">
          <v-col class="ml-10">
            <v-icon>mdi-eye</v-icon>
            {{ article.watchNum }}
          </v-col>
          <v-col class="d-flex justify-end align-center mr-10">
            <v-icon size="small">
              mdi-calendar-multiple
            </v-icon>
            {{ article.create_time }}
          </v-col>
        </v-row>
        <v-divider class="my-10" />
        <v-row>
          <v-col>
            <v-md-preview :text="content" />
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

const content = ref()


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
