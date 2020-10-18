$(document).ready(function(){
    window.configManager = new Vue({
        el: "#config_app",
        template: '<config-manager v-bind:config="config" />',
        components: {
            'config-manager': httpVueLoader("/js/components/config_manager.vue")
        },
        data: function() {
            return {
                config: {
                    debug: false,
                    properties: [],
                    services: [],
                    commonConfig: [],
                    servicesConfig: []
                }
            }
        }
    })
});