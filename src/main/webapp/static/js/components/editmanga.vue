<template>
<div class="editing-container">
	<manga :manga="mangaEditing"></manga>
        
	<div v-show="canSave">
		<button class="btn btn-primary" type="button" @click="save"> 
			Save
		</button>
	</div>
</div>
</template>

<script>
	/** componente de edição de manga */
	import C from '../constants.vue'
	import Vue from 'vue'
	import store from '../vuex/store.vue'
	import utils from '../services/utils.vue'
	
	var data = {
		emptyManga: {
			source: '',
			name: '',
			authors: [],
			genres: [],
			mangaChapters: []
		}
	}
	
	export default {
		data() {
			return data;
		},
		props: ['mangaEditing'],
    	methods: {
	    	resetManga() {
	    		utils.copyManga(this.emptyManga, this.mangaEditing);
	    	},
	    	_canSave() {
	    		return utils.canSaveManga(this.mangaEditing);
	    	},
	    	save() {
	    		if(!this._canSave()) {
	    			return;
	    		}
	    		var editedManga = {}
	    		utils.copyManga(this.mangaEditing, editedManga);
	    		this.$store.dispatch('saveManga', editedManga).then(response => {
					this.resetManga();
	    		}, error => {
					//	    		
	    		});
	    	}
		},
		computed: {
			canSave() {
				return this._canSave();
			},
			editing() {
				return this.$store.getters.editing;
			}
		}
	}
</script>