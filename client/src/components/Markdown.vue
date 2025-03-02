<template>
  <v-container>

    <v-md-editor
      v-model="text"
      :include-level="[1, 2, 3, 4]"
      disabled-menus="[]"
      @upload-image="uploadImage"
    >

    </v-md-editor>
  </v-container>

</template>

<script lang="ts" setup>


function uploadImage(event: Event, insertImage: (url: string) => void, file: File) {

  console.log(event, insertImage, file)
  // insertImage(
  //   "https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
  // )

  //TODO 插入图片然后上传到服务器

}

const text = ref("")
const props = defineProps<{ submit: boolean }>()
watchEffect(() => {
  if (props.submit) {
    console.log("props.submit", props.submit)

    //TODO 把文章发送到服务器
  }
})

</script>
