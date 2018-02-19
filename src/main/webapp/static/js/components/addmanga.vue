<template>
<div class="editing-container" v-show="isActive">
	<manga :manga="newManga"></manga>

	<div class="input-group-btn" v-show="fieldsDefined"> 
		<button @click="saveNewManga" class="js-add btn btn-primary"
			type="button">Save new manga</button> 
    </div>
</div>
</template>

<script>
	/** componente para adição de mangas */
	import C from '../constants.vue'
	import Vue from 'vue'
	import store from '../vuex/store.vue'
	import navigationmixin from '../services/navigationmix.vue'
	import utils from '../services/utils.vue'
	
	var data = {
		newManga: {
			name: '',
			source: '',
			authors: [],
			genres: [],
			mangaChapters: []
		},
		emptyManga: {
			name: '',
			source: '',
			authors: [],
			genres: [],
			mangaChapters: []
		},
		componentName: C.components.AddManga
	};
	
	export default {
		data() {
			return data;
		},
		mixins: [navigationmixin],
		methods: {
			saveNewManga() {
				var newManga = {};
				utils.copyManga(this.newManga, newManga);
				this.$store.dispatch('saveNewManga', newManga).then(response => {
					utils.copyManga(this.emptyManga, this.newManga);
				}, error => {
					// 
				});
			}
		},
		computed: {
			fieldsDefined() {
				var canSave = utils.canSaveManga(this.newManga);
				return canSave;
			},
		}
	}
</script>