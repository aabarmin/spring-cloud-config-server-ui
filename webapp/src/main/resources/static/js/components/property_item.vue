<template>
  <li class="list-group-item">
    <div class="row">
      <div class="col-sm-5">
        <p>
          <strong>
            {{property.property.key}}
          </strong>
        </p>
        <p>
          {{property.property.description}}
        </p>
      </div>
      <div class="col-sm-6">
        <input v-model="property.value" class="form-control">
      </div>
      <div class="col-sm-1">
        <button class="btn btn-danger" v-on:click="$emit('remove-property', property.property.key)">
          Delete
        </button>
      </div>
    </div>
  </li>
</template>

<script>
module.exports = {
  props: ['property']
}
</script>