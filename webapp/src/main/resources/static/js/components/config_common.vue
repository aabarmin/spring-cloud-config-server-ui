<template>
  <div>
    <div class="row">
      <div class="col-sm-12">
        <property-appender v-bind:all-properties="availableProperties"
                           v-bind:used-properties="values" />
      </div>
    </div>
    <div class="row">
      <div class="col-sm-12">
        <property-list v-bind:properties="values" />
      </div>
    </div>
  </div>
</template>

<script>
module.exports = {
  props: ['values', 'properties'],
  data: function(){
    return {

    }
  },
  computed: {
    availableProperties: function() {
      return this.properties.filter(prop => {
        return prop.isCommon === true;
      });
    }
  },
  components: {
    'property-list': httpVueLoader("/js/components/property_list.vue"),
    'property-appender': httpVueLoader("/js/components/property_appender.vue")
  }
}
</script>