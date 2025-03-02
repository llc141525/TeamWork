<template>
  <!--  文章首页-->
  <v-app>
    <v-container>
      <v-row class="my-10">
        <v-col v-for="i in 10" :key="i" cols="4">
          <article-show :article_show="article"
                        @click="router.push(`/articles/${i}`)"
          />
        </v-col>
      </v-row>
    </v-container>

  </v-app>
</template>

<script lang="ts" setup>
import type IArticleShow from "@/types/IArticleShow";

const router = useRouter()
const article = reactive<IArticleShow>({
  title: 'Garmins Instinct Crossover is a rugged hybrid smartwatch',
  category: 'pwn',
  create_time: '2024-01-01',
  author: 'hello',
  watchNum: 10,
})

//
</script>
