<template>
  <div class="form-group row">
    <label class="col-sm-2 col-form-label">Available properties:</label>
    <select class="form-control col-sm-8" v-model="selectedType">
      <option v-for="property in availableProperties"
              v-bind:value="property.key"
              :key="property.key">
        {{property.key}} - {{property.description}}
      </option>
    </select>
    <div class="col-sm-2">
      <button type="button" class="btn btn-primary" v-on:click="addProperty">
        Add
      </button>
    </div>
  </div>
</template>

<script>
module.exports = {
  props: ['allProperties', 'usedProperties'],
  data: function(){
    return {
      selectedProperty: ''
    }
  },
  computed: {
    availableProperties: function() {
      return this.allProperties.filter(prop => {
        let used = this.usedProperties.filter(selected => {
          return selected.property.key == prop.key;
        });
        return (used.length == 0);
      });
    },
    selectedType: {
      get: function() {
        if (this.availableProperties.length > 0) {
          this.selectedProperty = this.availableProperties[0].key;
          return this.selectedProperty;
        }
        return '';
      },
      set: function(newValue) {
        this.selectedProperty = newValue;
      }
    }
  },
  methods: {
    addProperty: function() {
      let property = this.allProperties.filter(prop => {
        return prop.key == this.selectedProperty;
      })[0];

      this.usedProperties.push({
        property: property,
        value: ''
      });
    }
  },
}
</script>