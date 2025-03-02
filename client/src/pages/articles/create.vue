<template>

  <v-container>
    <v-row class="d-flex justify-center font-weight-bold text-h3 my-10">
      新建文章
    </v-row>
    <Markdown :submit="handle_submit"/>
    <v-row class="d-flex justify-center mt-10">
      <v-btn color="primary" @click="submit">submit</v-btn>
    </v-row>

  </v-container>
</template>

<script lang="ts" setup>
const handle_submit = ref(false)


function submit() {
//
  handle_submit.value = !handle_submit.value
  // console.log("父组件", handle_submit.value)
}
</script>


<style lang="sass" scoped>

</style>
