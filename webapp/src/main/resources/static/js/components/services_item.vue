<template>
  <div class="card">
    <div class="card-header">
      <button class="btn btn-link btn-block text-left"
              data-toggle="collapse"
              v-bind:data-target="expandTarget">
        {{service.service.name}}
      </button>
    </div>

    <div class="collapse"
         v-bind:id="expandId">
      <div class="card-body">
        <div class="row">
          <div class="col-sm-10">
            <p>
              <strong>
                {{service.service.name}}
              </strong>
            </p>
            <p>
              {{service.service.description}}
            </p>
          </div>
          <div class="col-sm-2">
            <button class="btn btn-danger" v-on:click="$emit('service-remove', service.service.key)">
              Remove
            </button>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <property-appender v-bind:all-properties="availableProperties"
                               v-bind:used-properties="service.properties" />
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <property-list v-bind:properties="service.properties" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
module.exports = {
  props: ['service', 'properties'],
  computed: {
    expandTarget: function() {
      return "#" + this.service.service.key;
    },
    expandId: function() {
      return this.service.service.key;
    },
    availableProperties: function() {
      return this.properties.filter(prop => {
        return prop.isCommon === false &&
            (prop.services.indexOf(this.service.service.key) != -1)
      });
    }
  },
  methods: {

  },
  components: {
    'property-appender': httpVueLoader("/js/components/property_appender.vue"),
    'property-list': httpVueLoader("/js/components/property_list.vue")
  }
}
</script>