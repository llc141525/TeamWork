<template>

  <v-hover v-slot="{ isHovering, props:hoverProps }">
    <v-card
      :elevation="isHovering ? 10 : 2"
      class="position-relative"
      href="localhost:8080/articles/1"
      max-height="450"
      max-width="350"
      rounded="lg"
      style="z-index: 1"
      v-bind="hoverProps"
      @click="() => {router.push('/articles/1')}"
    >
      <v-avatar class="position-absolute" style="top: 200px; left: 20px; z-index: 4">
        <v-img
          src="https://cdn.vuetifyjs.com/images/john.jpg"
        ></v-img>
      </v-avatar>
      <v-img :class="{ 'hover-zoom': isHovering }"
             :sizes="img_size"
             cover
             max-height="225"
             src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
             style="z-index: 2; overflow: visible"
      >
      </v-img>

      <v-card-text class="pt-6 my-2" style="z-index: 3 ;position: relative">
        <v-row no-gutters>
          <v-col class="ml-2">
            <v-chip class="pa-2" color="primary">{{ prop.article_show.category }}</v-chip>
          </v-col>
          <v-col class="justify-end align-center d-flex mr-2">
            <v-icon icon="mdi-eye" size="small"></v-icon>
            {{ prop.article_show.watchNum }}
          </v-col>
        </v-row>

        <v-row no-gutters>
          <v-col>
            <div class="text-h6 pa-2">{{ prop.article_show.title }}</div>
          </v-col>
        </v-row>

        <v-row class="my-2" no-gutters>
          <v-col class="pl-2">
            <v-icon size="small">mdi-calendar-multiple</v-icon>
            {{ prop.article_show.create_time }}
          </v-col>
          <v-col class="justify-end align-center d-flex">
            <v-icon icon="mdi-account" size="small"></v-icon>
            {{ prop.article_show.author }}
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-hover>


</template>

<script lang="ts" setup>
import type IArticleShow from "@/types/IArticleShow";

const router = useRouter()
const img_size = ref("100")
const prop = defineProps<{ article_show: IArticleShow }>()
</script>


<style lang="scss" scoped>
.hover-zoom {
  transition: transform 0.3s ease-in-out;
  transform-origin: center;

  &:hover {
    transform: scale(1.1);
    z-index: 10;
  }
}
</style>
