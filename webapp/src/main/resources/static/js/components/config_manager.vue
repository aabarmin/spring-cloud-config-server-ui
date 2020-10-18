<template>
  <div>
    <h2>
      Configuration Management
    </h2>

    <div class="accordion">
      <div class="card">
        <div class="card-header">
          <button class="btn btn-link btn-block text-left" data-toggle="collapse" data-target="#commonSection">
            Common Configuration
          </button>
        </div>

        <div id="commonSection" class="collapse">
          <div class="card-body">
            <config-common v-bind:values="config.commonConfig"
                           v-bind:properties="config.properties" />
          </div>
        </div>
      </div>
      <div class="card">
        <div class="card-header">
          <button class="btn btn-link btn-block text-left" data-toggle="collapse" data-target="#servicesSection">
            Services
          </button>
        </div>
        <div id="servicesSection" class="collapse show">
          <div class="card-body">
            <div>
              <services-appender v-bind:services="config.services"
                                 v-bind:services-config="config.servicesConfig" />
            </div>
            <div>
              <services-list v-bind:services="config.servicesConfig"
                             v-bind:properties="config.properties" />
            </div>
          </div>
        </div>
      </div>
    </div>

    <pre v-if="config.debug === true">{{config}}</pre>
  </div>
</template>

<script>
module.exports = {
  props: ['config'],
  data: function(){
    return {

    }
  },
  components: {
    'config-common': httpVueLoader("/js/components/config_common.vue"),
    'services-list': httpVueLoader("/js/components/services_list.vue"),
    'services-appender': httpVueLoader("/js/components/services_appender.vue")
  }
}
</script>