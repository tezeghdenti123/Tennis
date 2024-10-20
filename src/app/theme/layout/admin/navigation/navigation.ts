export interface NavigationItem {
  id: string;
  title: string;
  type: 'item' | 'collapse' | 'group';
  translate?: string;
  icon?: string;
  hidden?: boolean;
  url?: string;
  classes?: string;
  exactMatch?: boolean;
  external?: boolean;
  target?: boolean;
  breadcrumbs?: boolean;
  badge?: {
    title?: string;
    type?: string;
  };
  children?: NavigationItem[];
}

export const NavigationItems: NavigationItem[] = [
  {
    id: 'navigation',
    title: 'Navigation',
    type: 'group',
    icon: 'icon-group',
    children: [
      {
        id: 'dashboard',
        title: 'Dashboard',
        type: 'item',
        url: '/analytics',
        icon: 'feather icon-home'
      }
    ]
  },
  {
    id: 'ui-component',
    title: 'Administration',
    type: 'group',
    icon: 'icon-group',
    children: [
      {
        id: 'basic',
        title: 'Administration',
        type: 'collapse',
        icon: 'feather icon-box',
        children: [
          {
            id: 'button',
            title: 'Button',
            type: 'item',
            url: '/component/button'
          },
          {
            id: 'badges',
            title: 'Ajouter',
            type: 'item',
            url: '/component/badges'
          },
          {
            id: 'Consulter',
            title: 'Consulter',
            type: 'item',
            url: '/component/collapse'
          },

        ]
      }
    ]
  },
  {
    id: 'Client',
    title: 'Clients',
    type: 'group',
    icon: 'icon-group',
    children: [
      {
        id: 'basic',
        title: 'Clients',
        type: 'collapse',
        icon: 'feather icon-box',
        children: [
          {
            id: 'Ajouter',
            title: 'Ajouter',
            type: 'item',
            url: '/client/ajouter'
          },
          {
            id: 'Afficher',
            title: 'Afficher',
            type: 'item',
            url: '/client/afficher'
          },
          {
            id: 'Groupes',
            title: 'Groupes',
            type: 'item',
            url: '/client/groupe'
          },

        ]
      }
    ]
  },
  {
    id: 'Coach',
    title: 'Coach',
    type: 'group',
    icon: 'icon-group',
    children: [
      {
        id: 'basic',
        title: 'Coachs',
        type: 'collapse',
        icon: 'feather icon-box',
        children: [
          {
            id: 'Ajouter',
            title: 'Ajouter',
            type: 'item',
            url: '/coach/ajouter'
          },
          {
            id: 'Afficher',
            title: 'Afficher',
            type: 'item',
            url: '/coach/afficher'
          }

        ]
      }
    ]
  },
  {
    id: 'Reservation',
    title: 'Reservation',
    type: 'group',
    icon: 'icon-group',
    children: [
      {
        id: 'basic',
        title: 'Reservation',
        type: 'collapse',
        icon: 'feather icon-box',
        children: [
          {
            id: 'Ajouter',
            title: 'Ajouter',
            type: 'item',
            url: '/affectation/ajouter'
          },
          {
            id: 'Afficher',
            title: 'Afficher',
            type: 'item',
            url: '/affectation/afficher'
          }

        ]
      }
    ]
  },
  {
    id: 'Authentication',
    title: 'Authentication',
    type: 'group',
    icon: 'icon-group',
    children: [
      {
        id: 'signup',
        title: 'Sign up',
        type: 'item',
        url: '/auth/signup',
        icon: 'feather icon-at-sign',
        target: true,
        breadcrumbs: false
      },
      {
        id: 'signin',
        title: 'Sign in',
        type: 'item',
        url: '/auth/signin',
        icon: 'feather icon-log-in',
        target: true,
        breadcrumbs: false
      }
    ]
  },
  {
    id: 'chart',
    title: 'Chart',
    type: 'group',
    icon: 'icon-group',
    children: [
      {
        id: 'apexchart',
        title: 'ApexChart',
        type: 'item',
        url: '/chart',
        classes: 'nav-item',
        icon: 'feather icon-pie-chart'
      }
    ]
  },
  {
    id: 'other',
    title: 'Other',
    type: 'group',
    icon: 'icon-group',
    children: [
      {
        id: 'Calendrier',
        title: 'Calendrier',
        type: 'item',
        url: '/sample-page',
        classes: 'nav-item',
        icon: 'feather icon-sidebar'
      }
        
      
    ]
  }
];
