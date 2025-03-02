// main.ts
import {createApp} from 'vue'
import App from './App.vue'
import {registerPlugins} from '@/plugins'
import VueMarkdownEditor from '@kangc/v-md-editor'
import VMdPreview from '@kangc/v-md-editor/lib/preview'
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js'
import '@kangc/v-md-editor/lib/style/base-editor.css'
import '@kangc/v-md-editor/lib/theme/style/github.css'
import hljs from 'highlight.js/lib/core'
import python from 'highlight.js/lib/languages/python'
import javascript from 'highlight.js/lib/languages/javascript'
import createLineNumberPlugin from '@kangc/v-md-editor/lib/plugins/line-number/index';
import java from 'highlight.js/lib/languages/java'
import cpp from 'highlight.js/lib/languages/cpp'
import bash from 'highlight.js/lib/languages/bash'

hljs.registerLanguage('python', python)
hljs.registerLanguage('javascript', javascript)
hljs.registerLanguage('java', java)
hljs.registerLanguage('cpp', cpp)
hljs.registerLanguage('bash', bash)

// 使用 Highlight.js 作为代码高亮库
VueMarkdownEditor.use(githubTheme, {Hljs: hljs})
VueMarkdownEditor.use(createLineNumberPlugin())
VMdPreview.use(githubTheme, {Hljs: hljs})
VMdPreview.use(createLineNumberPlugin())

const app = createApp(App)
app.use(VMdPreview)
app.use(VueMarkdownEditor)

registerPlugins(app)
app.mount('#app')
