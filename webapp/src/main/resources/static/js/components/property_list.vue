<template>
  <ul class="list-group">
    <property-item v-for="prop in properties"
                   v-bind:property="prop"
                   v-on:remove-property="removeProperty"
                   :key="prop.key" />
  </ul>
</template>

<script>
module.exports = {
  props: ['properties'],
  data: function() {
    return {}
  },
  methods: {
    removeProperty: function(toRemoveKey){
      for (let index = 0; index < this.properties.length; index++) {
        let property = this.properties[index];
        if (property.property.key == toRemoveKey) {
          this.properties.splice(index, 1);
        }
      }
    }
  },
  components: {
    'property-item': httpVueLoader("/js/components/property_item.vue")
  }
}
</script>