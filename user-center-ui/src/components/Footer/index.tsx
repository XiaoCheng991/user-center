import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import { useIntl } from 'umi';

const Footer: React.FC = () => {
  const intl = useIntl();
  const defaultMessage = intl.formatMessage({
    id: 'app.copyright.produced',
    defaultMessage: '强哥体验出品',
  });

  const currentYear = new Date().getFullYear();

  return (
    <DefaultFooter
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'github',
          title: '抖音',
          href: 'https://www.douyin.com/?ug_source=pc_liangxiang_01',
          blankTarget: true,
        },
        {
          key: 'github',
          title: <GithubOutlined />,
          href: 'https://github.com/ant-design/ant-design-pro',
          blankTarget: true,
        },
        {
          key: 'Coder-Website',
          title: '程序员网站',
          href: 'https://github.com/tuteng/Best-websites-a-programmer-should-visit-zh',
          blankTarget: true,
        },
      ]}
    />
  );
};

export default Footer;
