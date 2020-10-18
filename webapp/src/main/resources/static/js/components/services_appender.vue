<template>
  <div class="form-group row">
    <label class="col-sm-2 col-form-label">Available services:</label>
    <select class="form-control col-sm-8" v-model="selectedService">
      <option v-for="service in availableServices"
              v-bind:value="service.key"
              :key="service.key">
        {{service.name}}
      </option>
    </select>
    <div class="col-sm-2">
      <button type="button"
              class="btn btn-primary"
              v-on:click="appendService">
        Add
      </button>
    </div>
  </div>
</template>

<script>
module.exports = {
  props: ['services', 'servicesConfig'],
  data: function() {
    return {
      _selectedService: ''
    }
  },
  methods: {
    appendService: function() {
      let selected = this.services.filter(service => {
        return service.key == this._selectedService
      })[0];

      this.servicesConfig.push({
        service: selected,
        properties: []
      });
    }
  },
  computed: {
    availableServices: function() {
      return this.services.filter(service => {
        let used = this.servicesConfig.filter(config => {
          return service.key == config.service.key;
        });
        return used.length == 0;
      });
    },
    selectedService: {
      get: function() {
        let available = this.availableServices;
        if (available.length > 0) {
          let key = available[0].key;
          this._selectedService = key;
          return key;
        }
        return '';
      },
      set: function(newValue) {
        this._selectedService = newValue;
      }
    }
  }
}
</script>