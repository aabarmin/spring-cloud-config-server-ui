$(document).ready(function(){
    new Vue({
        el: "#config_app",
        template: '<config-manager v-bind:config="config" />',
        components: {
            'config-manager': httpVueLoader("/js/components/config_manager.vue")
        },
        data: function() {
            return {
                config: {
                    properties: [
                        {
                            key: "common.key.1",
                            description: "common description 1",
                            isCommon: true,
                            services:  []
                        },
                        {
                            key: "common.key.2",
                            description: "common description 2",
                            isCommon: true,
                            services:  []
                        },
                        {
                            key: "shared.somehow.1",
                            description: "this property is shared across services",
                            isCommon: false,
                            services:  []
                        }
                    ],
                    services: [
                        {
                            key: "service-ingest-alerter",
                            name: "Service Ingest Alerter",
                            description: "This service does something"
                        },
                        {
                            key: "service-ingest-metadata-extractor",
                            name: "Service Ingest Metadata Extractor",
                            description: "This service extracts metadata"
                        }
                    ],
                    commonConfig: [
                        {
                            property: {
                                key: "common.key.1",
                                description: "common description 1",
                                isCommon: true,
                                services:  []
                            },
                            value: "some another value"
                        },
                        {
                            property: {
                                key: "common.key.99",
                                description: "this property is not in the list of properties",
                                isCommon: true,
                                services:  []
                            },
                            value: "some another value"
                        }
                    ],
                    servicesConfig: [
                        {
                            service: {
                                key: "service-ingest-type-identifier",
                                name: "Service Ingest Type Identifier",
                                description: "This service wasn't present here before"
                            },
                            properties: []
                        }
                    ]
                }
            }
        }
    })
});