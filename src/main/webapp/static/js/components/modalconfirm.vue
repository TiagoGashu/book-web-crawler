<template>
<div class="modal-confirm modal" v-if="params.showModal">
	<div class="modal-background">
		<!-- backround transparente -->
	</div>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" v-if="hasTitle()">
				{{title}}
			</div>
		
			<div class="modal-body">
				{{body}}
			</div>
		
			<div class="modal-footer">
			
				<span @click="confirm">
					<button class="btn btn-primary">{{confirmLabel}}</button>
				</span>
			
				<span @click="cancel">
					<button class="btn">{{cancelLabel}}</button>
				</span>
			</div>
		</div>
	</div>
</div>
</template>

<script>
/**
	param: {
		showModal: Boolean,
		title: string,
		bodyMessage: String,
		confirmLabel: String,
		cancelLabel: String,
		confirmCallback: Function,
		cancelCallback: Function
	}
*/
import store from '../vuex/store.vue'

var data = {
	defaultTitle: '',
	defaultBodyMessage: 'Confirme a ação',
	defaultConfirmLabel: 'YASZ',
	defaultCancelLabel: 'Cancel'
}

export default {
	data() {
		return data;
	},
	props: ['params'],
	methods: {
		hasTitle() {
			return this.title && this.title != '';
		},
		confirm() {
			this.params.confirmCallback();
			this.params.showModal = false;
		},
		cancel() {
			this.params.cancelCallback();
			this.params.showModal = false;
		}
	},
	computed: {
		title() {
			return this.params.title ? this.params.title : this.defaultTitle;
		},
		body() {
			return this.params.bodyMessage ? this.params.bodyMessage : this.defaultBodyMessage;
		},
		confirmLabel() {
			return this.params.confirmLabel ? this.params.confirmLabel : this.defaultConfirmLabel;
		},
		cancelLabel() {
			return this.params.cancelLabel ? this.params.cancelLabel : this.defaultCancelLabel;
		}
	}
}
</script>

<style>
	.modal.modal-confirm {
		z-index: 1050;
		display: inline-block !important;
	}
	.modal-dialog {
		margin-top: 20%;
	}
	.modal.modal-confirm .modal-background {
		width: 100%;
		height: 100%;
		position: absolute;
		background: rgba(135, 155, 169, 0.5)
	}
</style>