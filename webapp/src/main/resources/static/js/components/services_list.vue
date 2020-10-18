<template>
  <div class="accordion">
    <services-item v-for="service in services"
                   v-bind:service="service"
                   v-on:service-remove="removeService"
                   :key="service.service.key" />
  </div>
</template>

<script>
module.exports = {
  props: ['services', 'properties'],
  methods: {
    removeService: function(serviceKey) {
      for (let index = 0; index < this.services.length; index++) {
        let service = this.services[index];
        if (service.service.key == serviceKey) {
          this.services.splice(index, 1);
        }
      }
    }
  },
  components: {
    'services-item': httpVueLoader("/js/components/services_item.vue")
  }
}
</script>