import App from "./App.vue";
import router from "./router";

import { createApp } from "vue";
import { createPinia } from "pinia";

// import "vuetify/styles";
// import { createVuetify } from "vuetify";
// import * as components from "vuetify/components";
// import * as directives from "vuetify/directives";

//fort-awesome
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import { fas } from "@fortawesome/free-solid-svg-icons";

library.add(fas);

////////

const app = createApp(App);

// const vuetify = createVuetify({
//   components,
//   directives,
// });

app.use(createPinia());
app.use(router);
// app.use(vuetify);
app.component("font-awesome-icon", FontAwesomeIcon);
app.mount("#app");
